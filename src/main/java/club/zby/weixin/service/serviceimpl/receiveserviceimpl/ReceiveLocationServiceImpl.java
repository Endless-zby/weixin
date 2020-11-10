package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveLocation;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */
@Component
public class ReceiveLocationServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveLocation receiveLocation = JSON.parseObject(postData, ReceiveLocation.class);
        System.out.println(receiveLocation);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveLocation receiveLocation = JSON.parseObject(postData, ReceiveLocation.class);
        return null;
    }
}
