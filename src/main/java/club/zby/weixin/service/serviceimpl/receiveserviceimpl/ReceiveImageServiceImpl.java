package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveImage;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 图片消息处理
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
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveImage,receiveResult);
        //接收到的图片原样返回
        return receiveResult.imageTemplate(receiveImage.getMediaId());
    }
}
