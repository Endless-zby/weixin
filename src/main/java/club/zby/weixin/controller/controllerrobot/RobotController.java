package club.zby.weixin.controller.controllerrobot;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.RobotTemplate;
import club.zby.weixin.service.RobotService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.ws.soap.Addressing;
import java.util.*;

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


    @Data
    @AllArgsConstructor
    static class Demo{
        private String name;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class Demo2{
        private String name2;
        private String password2;
    }
    public static <T> List<JSONArray> getjson(List<T> arr){
        List<JSONArray> maps = new ArrayList<>();
        Gson gson = new Gson();
        for (T item : arr) {
            String json = gson.toJson(item);
            maps.add(new JSONArray(Collections.singletonList(json)));
        }
        // 这个地方重新构建树
        return maps;
    }
    public static void main(String[] args) {
        List<Demo> list = new ArrayList<>();
        List<Demo2> list2 = new ArrayList<>();
        list.add(new Demo("byzhao", "zby123456"));
        list2.add(new Demo2("jinhui", "44444"));
        List<JSONArray> getjson = getjson(list);
        List<JSONArray> getjson2 = getjson(list2);
        System.out.println(getjson);
        System.out.println(getjson2);

    }

}
