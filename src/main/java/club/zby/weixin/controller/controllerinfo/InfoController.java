package club.zby.weixin.controller.controllerinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */

@Controller
@RequestMapping(value = "/appchat")
public class InfoController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Value("${POST_APPCHAT_CREATE}")
    private String POST_APPCHAT_CREATE; //创建群聊会话
    @Value("${GET_APPCHAT_GET}")
    private String GET_APPCHAT_GET; //获取群聊会话
    @Value("${POST_APPCHAT_SEND}")
    private String POST_APPCHAT_SEND;   //发送群聊消息


    /**
     * 企业微信群 创建
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/create")
    public String create(){
        return null;
    }

    /**
     * 企业微信群 获取
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/get")
    public String get(){
        return null;
    }

    /**
     * 企业微信群 消息发送
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/send")
    public String send(){
        return null;
    }


    
}
