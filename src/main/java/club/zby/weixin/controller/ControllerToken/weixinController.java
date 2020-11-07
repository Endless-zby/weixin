package club.zby.weixin.controller.ControllerToken;

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

@Controller
@RequestMapping("/weixin")
public class weixinController {

    @Value("${token}")
    private String sToken;
    @Value("${EncodingAESKey}")
    private String sEncodingAESKey;
    @Value("${corpid}")
    private String sCorpID; //企业ID
    @Resource
    private AccessTokenServiceImpl accessTokenService;

    /**
     * 回调配置
     * @param wxVerifyIn
     * @return
     * @throws AesException
     */
    @ResponseBody
    @GetMapping(value = "/getToken")
    public String getToken(WXVerifyIn wxVerifyIn) throws AesException {

        /**
         * 1.对收到的请求做Urldecode处理
         * 2.通过参数msg_signature对请求进行校验，确认调用者的合法性。
         * 3.解密echostr参数得到消息内容(即msg字段)
         * 4.在1秒内原样返回明文消息内容(不能加引号，不能带bom头，不能带换行符)
         */
        String sEchoStr; //需要返回的明文
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        try {
            sEchoStr = wxcpt.VerifyURL(wxVerifyIn);
            System.out.println("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            return sEchoStr;
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            return null;
        }

    }

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
