package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.entity.receivemessages.ReceiveVoice;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 媒体消息处理
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */

public class ReceiveVoiceServiceImpl implements ReceiveService {
    @Override
    public ReceiveVoice printReceive(String postData) {
        return JSON.parseObject(postData, ReceiveVoice.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveVoice receiveVoice = JSON.parseObject(postData, ReceiveVoice.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveVoice,receiveResult);
        return receiveResult.textTemplate("这是一段语音，语音格式是" + receiveVoice.getFormat());
    }
}
