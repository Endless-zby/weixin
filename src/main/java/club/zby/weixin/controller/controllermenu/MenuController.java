package club.zby.weixin.controller.controllermenu;


import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MenuButton;
import club.zby.weixin.entity.MenuTemplate;
import club.zby.weixin.service.MenuService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */

@Controller
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private ApplicationContext applicationContext;

    @ResponseBody
    @PostMapping("create")
    public ApiRespones create(@RequestParam("agentId") String agentId) throws Exception {
        MenuButton menu = createMenuDemo();
        ApiRespones apiRespones = menuService.createMenu(menu, agentId);
        ArrayList<MenuTemplate> menuTemplateAll = new ArrayList<>();
        if(apiRespones.isSuccess()){
            ArrayList<MenuTemplate> menuTemplates = new ArrayList<>(menu.getButton());
            getNextMenuTemplate(menuTemplates,menuTemplateAll);
            //将菜单设置保存到数据库（先存到redis）
            menuTemplateAll.forEach(menuTemplate -> {
                redisTemplate.opsForValue().set(menuTemplate.getKey(),menuTemplate);
                applicationContext.publishEvent(menuTemplate);
            });
            return apiRespones;
        }
        return new ApiRespones("错误");
    }


    @ResponseBody
    @GetMapping("get")
    public ApiRespones get(@RequestParam("agentId") String agentId) throws Exception {
        return menuService.getAllMenuList(agentId);
    }

    /**
     * 深度遍历子节点菜单
     * @param menuTemplates
     * @param menuTemplateAll
     */
    public void getNextMenuTemplate(List<MenuTemplate> menuTemplates,ArrayList<MenuTemplate> menuTemplateAll){

        for (MenuTemplate menuTemplate : menuTemplates) {
            List<MenuTemplate> sub_button = menuTemplate.getSub_button();
            if(Objects.isNull(sub_button)){
                menuTemplateAll.add(menuTemplate);
            }else {
                getNextMenuTemplate(sub_button,menuTemplateAll);
            }
        }
    }


    public MenuButton createMenuDemo(){
        ArrayList<MenuTemplate> menuTemplates = new ArrayList<>();
        menuTemplates.add(MenuTemplate.builder().type(MenuTemplate.scancode_waitmsg).name("扫码带提示").key("rselfmenu_0_0").build());
        menuTemplates.add(MenuTemplate.builder().type(MenuTemplate.scancode_push).name("扫码推事件").key("rselfmenu_0_1").build());

        ArrayList<MenuTemplate> menuTemplateSends = new ArrayList<>();
        menuTemplateSends.add(MenuTemplate.builder().type(MenuTemplate.click).name("点个赞").key("V1001_GOOD").build());

        MenuButton menuButton = new MenuButton();

        MenuTemplate sao = MenuTemplate.builder().name("扫码").build();
        MenuTemplate send = MenuTemplate.builder().name("测试").build();
        sao.setSub_button(menuTemplates);
        send.setSub_button(menuTemplateSends);

        ArrayList<MenuTemplate> menus = new ArrayList<>();
        menus.add(sao);
        menus.add(send);

        menuButton.setButton(menus);
        return menuButton;
    }

}
