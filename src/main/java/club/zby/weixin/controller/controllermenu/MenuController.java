package club.zby.weixin.controller.controllermenu;


import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MenuButton;
import club.zby.weixin.entity.MenuTemplate;
import club.zby.weixin.service.AccessTokenService;
import club.zby.weixin.until.HttpUtiliy;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */

@Controller
@RequestMapping("menu")
public class MenuController<T> {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private HttpUtiliy httpUtiliy;
    @Value("${POST_MENU_CREATE}")
    private String POST_MENU_CREATE; //创建菜单
    @Value("${GET_MENU_GET}")
    private String GET_MENU_GET; //获取菜单


    @ResponseBody
    @PostMapping("create")
    public ApiRespones create() throws Exception {

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

        Map<String, Object> var = new HashMap<>();
        String accessToken = accessTokenService.getAccessToken();
        if(accessToken.isEmpty()){
            return new ApiRespones("获取accessToken失败！").build();
        }
        menuButton.setButton(menus);

        var.put("access_token",accessToken);
        var.put("agentid","1000002");
        ResponseEntity<ApiRespones> responseEntity = httpUtiliy.postForEntity(POST_MENU_CREATE, MediaType.APPLICATION_JSON_UTF8, JSON.toJSONString(menuButton), ApiRespones.class, var);
        return responseEntity.getBody();
    }


    @ResponseBody
    @GetMapping("get")
    public ApiRespones get() throws Exception {
        Map<String, Object> var = new HashMap<>();
        String accessToken = accessTokenService.getAccessToken();
        if(accessToken.isEmpty()){
            return new ApiRespones("获取accessToken失败！").build();
        }
        var.put("access_token",accessToken);
        var.put("agentid","1000002");
        ResponseEntity<MenuButton> entity = restTemplate.getForEntity(GET_MENU_GET, MenuButton.class, var);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }
        return new ApiRespones("错误").build();
    }

}
