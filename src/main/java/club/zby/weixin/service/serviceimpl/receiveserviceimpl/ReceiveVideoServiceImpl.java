package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveVoice;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */
@Component
public class ReceiveVideoServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveVoice receiveVoice = JSON.parseObject(postData, ReceiveVoice.class);
        System.out.println(receiveVoice);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveVoice receiveVoice = JSON.parseObject(postData, ReceiveVoice.class);
        return null;
    }
}
