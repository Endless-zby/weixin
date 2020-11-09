package club.zby.weixin.controller.controllerinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */

@Controller
@RequestMapping(value = "/appchat")
public class InfoController {

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
