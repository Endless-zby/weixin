package club.zby.weixin.controller.controllertoken;

import club.zby.weixin.entity.WXVerifyIn;
import club.zby.weixin.service.serviceimpl.AccessTokenServiceImpl;
import club.zby.weixin.until.aesconfig.AesException;
import club.zby.weixin.until.aesconfig.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 20:00
 */

@Controller
@RequestMapping("/weixin")
public class TokenController {

    @Resource
    private AccessTokenServiceImpl accessTokenService;

    /**
     * 获取access_token
     * @return
     * @throws Exception
     */

    @ResponseBody
    @GetMapping(value = "/getAccessToken")
    public String getAccessToken() throws Exception {
        return accessTokenService.getAccessToken();
    }





}
