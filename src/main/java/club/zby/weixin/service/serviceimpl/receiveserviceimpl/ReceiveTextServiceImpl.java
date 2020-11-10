package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.Receive;
import club.zby.weixin.entity.receivemessages.ReceiveText;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */
@Component
public class ReceiveTextServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveText receiveText = JSON.parseObject(postData, ReceiveText.class);
        System.out.println(receiveText.toString());

    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveText receiveText = JSON.parseObject(postData, ReceiveText.class);
        receiveText.setContent("sdsdsdsdsd");
        Document document = DocumentHelper.createDocument();
        System.out.println(receiveText);

        return "";
    }
}
