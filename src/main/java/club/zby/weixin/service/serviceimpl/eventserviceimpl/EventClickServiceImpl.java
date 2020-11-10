package club.zby.weixin.service.serviceimpl.eventserviceimpl;

import club.zby.weixin.entity.receivemessages.EventClick;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;

/**
 * @author 赵博雅
 * @date 2020/11/10 18:27
 */
public class EventClickServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        EventClick eventClick = JSON.parseObject(postData, EventClick.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        EventClick eventClick = JSON.parseObject(postData, EventClick.class);
        return "";
    }
}
