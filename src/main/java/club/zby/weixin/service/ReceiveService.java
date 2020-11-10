package club.zby.weixin.service;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:21
 */
public interface ReceiveService {

    void printReceive(String postData);

    String replyXmlInfo(String postData);
}
