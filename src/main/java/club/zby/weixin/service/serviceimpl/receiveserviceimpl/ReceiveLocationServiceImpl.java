package club.zby.weixin.service.serviceimpl.receiveserviceimpl;

import club.zby.weixin.entity.receivemessages.ReceiveLocation;
import club.zby.weixin.entity.receivemessages.ReceiveResult;
import club.zby.weixin.service.ReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 位置消息处理
 * @author 赵博雅
 * @date 2020/11/10 15:26
 */
@Component
public class ReceiveLocationServiceImpl implements ReceiveService {
    @Override
    public void printReceive(String postData) {
        ReceiveLocation receiveLocation = JSON.parseObject(postData, ReceiveLocation.class);
        System.out.println(receiveLocation);
    }

    @Override
    public String replyXmlInfo(String postData) {
        ReceiveLocation receiveLocation = JSON.parseObject(postData, ReceiveLocation.class);
        ReceiveResult receiveResult = new ReceiveResult();
        BeanUtils.copyProperties(receiveLocation,receiveResult);
        ArrayList<ReceiveResult.Articles> articles = new ArrayList<>();
        ReceiveResult.Articles articles1 = new ReceiveResult.Articles();
        articles1.setTitle("测试");
        articles1.setDescription("这是一个测试环境下的图文消息回复数据");
        articles1.setPicurl("https://www.zby123.club");
        articles1.setUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp1.qhimg.com%2Ft01f5c0dfbb5a886da7.jpg&refer=http%3A%2F%2Fp1.qhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616662196&t=6ef492ac118c2f5d4a717f09b4ea7661");
        articles.add(articles1);
        return receiveResult.newsTemplate(articles);

    }
}
