package club.zby.weixin.service;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MenuButton;
import org.springframework.stereotype.Service;

/**
 * @author 赵博雅
 * @date 2020/11/9 16:39
 */
@Service
public interface MenuService {

    /**
     * 注意：createMenu会清除现有的菜单结构
     * @param menuButton
     * @param agentId
     * @return
     * @throws Exception
     */
    ApiRespones createMenu(MenuButton menuButton,String agentId) throws Exception;  //注： 会清除现有的菜单结构

    ApiRespones getAllMenuList(String agentid) throws Exception; //注：agentid -> 应用id



}
