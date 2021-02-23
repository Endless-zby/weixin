package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.entity.receivemessages.ReceiveVideo;
import club.zby.weixin.entity.receivemessages.ReceiveVoice;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 视频消息处理
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */
@Component
public class ReceiveVideoServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveVideo receiveVideo = JSON.parseObject(postData, ReceiveVideo.class);
        System.out.println(receiveVideo);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveVideo receiveVideo = JSON.parseObject(postData, ReceiveVideo.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveVideo,receiveResult);
        return receiveResult.textTemplate("这是一段视频，视频三天内有效");
    }
}
