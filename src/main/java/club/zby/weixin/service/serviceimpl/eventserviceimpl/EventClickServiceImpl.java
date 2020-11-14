package club.zby.weixin.service.serviceimpl.eventserviceimpl;

import club.zby.weixin.entity.receivemessages.EventClick;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.service.ReceiveService;
import club.zby.weixin.until.XML;
import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 18:27
 */
@Component
public class EventClickServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        EventClick eventClick = JSON.parseObject(postData, EventClick.class);
    }

    @Override
    public String replyXmlInfo(String postData) {
        EventClick eventClick = JSON.parseObject(postData, EventClick.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(eventClick,receiveResult);
        receiveResult.textTemplate("这是一个测试数据");

        JSONObject jsonObject = new JSONObject(JSON.toJSONString(receiveResult));
        String xml  = XML.toString(jsonObject);
        return xml;
    }
}
