package club.zby.weixin.controller.controllerapns;

import club.zby.weixin.entity.WeiChatRespones;
import club.zby.weixin.service.MessageSendService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/apns")
public class APNsController {

    @Resource
    private MessageSendService messageSendService;

    @ResponseBody
    @GetMapping(value = "/send")
    public WeiChatRespones<Object> create(@RequestParam(defaultValue = "title") String title, @RequestParam(defaultValue = "Example!") String message, @RequestParam(defaultValue = "8a5c16959ae103b643bfe32c4af80b3b1d55f07f732f5ee600a67a6cf53d9aab") String token) throws Exception {
        messageSendService.send(title, message, token);
        return WeiChatRespones.isSuccess();
    }
}
