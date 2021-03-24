package club.zby.weixin.controller.test;

import club.zby.weixin.entity.interfaces.ReceiveInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 赵博雅
 * @date 2021/3/24 18:15
 */
@Controller
@RequestMapping
public class test {

    @ResponseBody
    @ReceiveInfo
    @GetMapping(value = "/test1")
    public String test1(String id) {
        return id;
    }

}
