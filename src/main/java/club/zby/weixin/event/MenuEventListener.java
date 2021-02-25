package club.zby.weixin.event;

import club.zby.weixin.entity.MenuTemplate;
import club.zby.weixin.entity.RobotTemplate;
import club.zby.weixin.service.RobotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 赵博雅
 * @date 2021/2/25 11:14
 */
@Async
@Component
public class MenuEventListener {

    @Resource
    private RobotService robotService;


    @EventListener
    public void send(MenuTemplate menuTemplate){
            ArrayList<RobotTemplate> receive = new ArrayList<>();
            receive.add(RobotTemplate.builder().color(RobotTemplate.COMMENT).title("新增菜单项").build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.WARNING).title("菜单响应类型：").value(menuTemplate.getType()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.INFO).title("菜单名称：").value(menuTemplate.getName()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.WARNING).title("菜单响应key：").value(menuTemplate.getKey()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.INFO).title("网页链接地址：").value(menuTemplate.getUrl()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.INFO).title("小程序页面路径：").value(menuTemplate.getPagepath()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.INFO).title("小程序appId：").value(menuTemplate.getAppid()).build());
            receive.add(RobotTemplate.builder().color(RobotTemplate.INFO).title("菜单子节点：").value(Objects.isNull(menuTemplate.getSub_button()) ? "无" : "有").build());
            robotService.robotToSendByMarkdown(receive);
        }
    }



