package club.zby.weixin.controller.test;

import club.zby.weixin.entity.MailBean;
import club.zby.weixin.entity.interfaces.AfterSendMessages;
import club.zby.weixin.entity.interfaces.ReceiveInfo;
import club.zby.weixin.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 赵博雅
 * @date 2021/3/24 18:15
 */
@Slf4j
@Controller
@RequestMapping
public class test {

    @Resource
    private EmailService emailService;

    @ResponseBody
    @ReceiveInfo
    @AfterSendMessages(topic = "测试")
    @GetMapping(value = "/test1")
    public String test1(String id) {
        MailBean mailBean = new MailBean();
        mailBean.setContent(id);
        mailBean.setRecipient("2220624782@qq.com");
        mailBean.setSubject("idea激活码");
        emailService.sendSimpleMail(mailBean);
        return id;
    }

    @PostMapping(value = "/test2")
    public String test2(@RequestBody String body, @RequestParam(value = "host") String host) {
        log.info("body - {}",body );
        log.info("host - {}",host );
        return host;
    }

}
