package club.zby.weixin.controller.controllerfanyi;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.FanYiRequest;
import club.zby.weixin.entity.MenuButton;
import club.zby.weixin.entity.MenuTemplate;
import club.zby.weixin.service.FanYiService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@RequestMapping
public class FanYiController {

    @Resource
    private FanYiService fanYiService;

    @ResponseBody
    @PostMapping("create")
    public String create(@RequestBody FanYiRequest fanYiRequest) throws Exception {
        String result = fanYiService.textTranslate(fanYiRequest.getSourceText(), fanYiRequest.getSource(), fanYiRequest.getTarget());
        return result;
    }

}
