package club.zby.weixin.service.serviceimpl;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MenuButton;
import club.zby.weixin.entity.UrlTemplateEnum;
import club.zby.weixin.service.AccessTokenService;
import club.zby.weixin.service.MenuService;
import club.zby.weixin.until.HttpUtiliy;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵博雅
 * @date 2020/11/9 16:44
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private HttpUtiliy httpUtiliy;

    /**
     * 创建菜单
     * @param menuButton
     * @param agentId
     * @return
     * @throws Exception
     */
    @Override
    public ApiRespones createMenu(MenuButton menuButton,String agentId) throws Exception {
        Map<String, Object> var = new HashMap<>();
        String accessToken = accessTokenService.getAccessToken();
        if(accessToken.isEmpty()){
            return new ApiRespones("获取accessToken失败！").build();
        }
        var.put("access_token",accessToken);
        var.put("agentid",agentId);
        ResponseEntity<ApiRespones> responseEntity = httpUtiliy.postForEntity(UrlTemplateEnum.POST_MENU_CREATE.getUrl(), MediaType.APPLICATION_JSON_UTF8, JSON.toJSONString(menuButton), ApiRespones.class, var);
        return responseEntity.getBody();

    }

    /**
     * 获取菜单结构
     * @param agentId
     * @return
     * @throws Exception
     */
    @Override
    public ApiRespones getAllMenuList(String agentId) throws Exception {
        Map<String, Object> var = new HashMap<>();
        String accessToken = accessTokenService.getAccessToken();
        if(accessToken.isEmpty()){
            return new ApiRespones("获取accessToken失败！").build();
        }
        var.put("access_token",accessToken);
        var.put("agentid",agentId);
        ResponseEntity<ApiRespones> forEntity = httpUtiliy.getForEntity(UrlTemplateEnum.GET_MENU_GET.getUrl(), ApiRespones.class, var);
        return forEntity.getBody();
    }
}
