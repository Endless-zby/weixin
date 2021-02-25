package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.Receive;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.entity.receivemessages.ReceiveText;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 文本消息处理
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */

public class ReceiveTextServiceImpl implements ReceiveService {

    @Override
    public ReceiveText printReceive(String postData) {
         return JSON.parseObject(postData, ReceiveText.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveText receiveText = JSON.parseObject(postData, ReceiveText.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveText,receiveResult);
        return receiveResult.textTemplate("这是一个正在测试的文本消息回复");
    }
}
