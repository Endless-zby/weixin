package club.zby.weixin.service;

import club.zby.weixin.entity.receivemessages.Receive;
import org.springframework.stereotype.Service;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:21
 */
@Service
public interface ReceiveService {

    Receive printReceive(String postData);

    String replyXmlInfo(String postData);



}
