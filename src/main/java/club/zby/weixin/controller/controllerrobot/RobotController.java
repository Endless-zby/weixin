package club.zby.weixin.controller.controllerrobot;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.RobotTemplate;
import club.zby.weixin.service.RobotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 20:00
 */

@Controller
@RequestMapping(value = "/webhook")
public class RobotController {

    @Resource
    private RobotService robotService;

    /**
     * 机器人 消息发布
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/send")
    public String send(){

        ArrayList<RobotTemplate> robotTemplates = new ArrayList<>();
        RobotTemplate build = RobotTemplate.builder().title("测试：").color(RobotTemplate.WARNING).value("这还是一个测试数据").build();
        RobotTemplate build1 = RobotTemplate.builder().title("测试1：").color(RobotTemplate.COMMENT).value("这还是一个测试数据").build();
        RobotTemplate build2 = RobotTemplate.builder().title("测试2：").color(RobotTemplate.WARNING).value("这还是一个测试数据").build();
        RobotTemplate build3 = RobotTemplate.builder().title("测试3：").color(RobotTemplate.INFO).value("这还是一个测试数据").build();
        robotTemplates.add(build);
        robotTemplates.add(build1);
        robotTemplates.add(build2);
        robotTemplates.add(build3);
        ApiRespones apiRespones = robotService.robotToSendByMarkdown(robotTemplates);
        if(apiRespones.isSuccess()){
            return "成功";
        }
        return "失败";
    }

}
