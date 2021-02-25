package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveLink;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 链接消息处理
 * @author 赵博雅
 * @date 2020/11/10 15:25
 */

public class ReceiveLinkServiceImpl implements ReceiveService {
    @Override
    public ReceiveLink printReceive(String postData) {
        return JSON.parseObject(postData, ReceiveLink.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveLink receiveLink = JSON.parseObject(postData, ReceiveLink.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveLink,receiveResult);
        return receiveResult.textTemplate("这是个链接消息，但是我不知道要做什么");
    }
}
