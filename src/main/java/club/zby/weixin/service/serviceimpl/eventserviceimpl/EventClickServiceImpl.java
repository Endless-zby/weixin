package club.zby.weixin.service.serviceimpl.eventserviceimpl;

import club.zby.weixin.entity.MenuTemplate;
import club.zby.weixin.entity.receivemessages.EventClick;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 赵博雅
 * @date 2020/11/10 18:27
 */

public class EventClickServiceImpl implements ReceiveService {

    @Override
    public EventClick printReceive(String postData) {
        return JSON.parseObject(postData, EventClick.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        EventClick eventClick = JSON.parseObject(postData, EventClick.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(eventClick,receiveResult);
        return receiveResult.textTemplate("点赞成功");
    }
}
