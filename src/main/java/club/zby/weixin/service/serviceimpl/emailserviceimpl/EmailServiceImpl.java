package club.zby.weixin.service.serviceimpl.emailserviceimpl;

import club.zby.weixin.entity.MailBean;
import club.zby.weixin.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 赵博雅
 * @date 2021/3/25 16:48
 */
@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Value(value = "${lance.mail.sender}")
    private String MAIL_SENDER;


    @Override
    public void sendSimpleMail(MailBean mailBean) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送人
        simpleMailMessage.setFrom(MAIL_SENDER);
        //邮件接收人
        simpleMailMessage.setTo(mailBean.getRecipient());
        //邮件主题
        simpleMailMessage.setSubject(mailBean.getSubject());
        //邮件内容
        simpleMailMessage.setText(mailBean.getContent());
        javaMailSender.send(simpleMailMessage);

    }
}
