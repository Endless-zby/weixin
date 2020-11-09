package club.zby.weixin.service;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.MenuButton;

/**
 * @author 赵博雅
 * @date 2020/11/9 16:39
 */
public interface MenuService {

    ApiRespones createMenu(MenuButton menuButton,String agentId) throws Exception;  //注： 会清除现有的菜单结构

    ApiRespones getAllMenuList(String agentid) throws Exception; //注：agentid -> 应用id



}
