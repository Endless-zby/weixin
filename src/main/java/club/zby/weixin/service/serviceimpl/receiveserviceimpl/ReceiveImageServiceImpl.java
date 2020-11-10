package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveImage;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:24
 */
@Component
public class ReceiveImageServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveImage receiveImage = JSON.parseObject(postData, ReceiveImage.class);
        System.out.println(receiveImage.toString());
        System.out.println(receiveImage.toSuperString());
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveImage receiveImage = JSON.parseObject(postData, ReceiveImage.class);
        return null;
    }
}
