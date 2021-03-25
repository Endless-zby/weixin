package club.zby.weixin.service;

import club.zby.weixin.entity.MailBean;
import org.springframework.stereotype.Service;

/**
 * @author 赵博雅
 * @date 2021/3/25 16:45
 */
@Service
public interface EmailService {

     void sendSimpleMail(MailBean mailBean);

}
