package club.zby.weixin.entity.interfaces;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MailBean;
import club.zby.weixin.entity.RobotTemplate;
import club.zby.weixin.service.EmailService;
import club.zby.weixin.service.RobotService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author 赵博雅
 * @date 2021/3/25 15:00
 */
@Aspect
@Slf4j
@Component
public class AfterSendMessagesMonitor {

    @Resource
    private RobotService robotService;
    @Resource
    private EmailService emailService;

    @AfterReturning(pointcut = "@annotation(AfterSendMessages)",returning = "result")
    public void afterSendMessages(JoinPoint joinPoint , Object result){
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        Method method = ms.getMethod();
        AfterSendMessages afterSendMessages = method.getAnnotation(AfterSendMessages.class);

        String sendResult = StringUtils.isEmpty(afterSendMessages.messages()) ? result.toString() : afterSendMessages.messages();
        boolean sendEmail = afterSendMessages.isSendEmail();
        // 发邮件给 afterSendMessages.toEmail()
        ArrayList<String> strings = new ArrayList<>();
        if(sendEmail){
            MailBean mailBean = new MailBean();
            mailBean.setSubject(afterSendMessages.topic());
            mailBean.setContent(result.toString());
            String[] toEmail = afterSendMessages.toEmail();
            for (String str: toEmail) {
                mailBean.setRecipient(str);
                emailService.sendSimpleMail(mailBean);
                strings.add(str);
            }
        }
        ArrayList<RobotTemplate> robotTemplates = new ArrayList<>();

        if(sendResult.length() > 4096 && sendEmail){
            RobotTemplate build2 = RobotTemplate.builder().title("更多：").value("消息超过指定长度【4096】，该消息已转发送至邮箱 -> " + strings + "中，请查收").color(RobotTemplate.WARNING).build();
            robotTemplates.add(build2);
        }else {
            RobotTemplate build1 = RobotTemplate.builder().title("返回消息：").value(sendResult).color(RobotTemplate.COMMENT).build();
            robotTemplates.add(build1);
        }
        //调企业微信机器人发送到群里
        ApiRespones apiRespones = robotService.robotToSendByMarkdown(robotTemplates);
        log.info("企业微信机器人消息发送code：{}，失败原因：{}",apiRespones.getErrcode(),apiRespones.getErrmsg());
    }
}
