package club.zby.weixin.controller.controllerreceive;

import club.zby.weixin.entity.WXVerifyIn;
import club.zby.weixin.until.aesconfig.AesException;
import club.zby.weixin.until.aesconfig.WXBizMsgCrypt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 15:16
 *
 * 说明：这个接口比较重要 会承担所有的由企业微信主动推送的消息   两个回调接口
 */

@Controller
@RequestMapping
public class ReceiveController {

    @Value("${token}")
    private String sToken;
    @Value("${EncodingAESKey}")
    private String sEncodingAESKey;
    @Value("${corpid}")
    private String sCorpID; //企业ID

    /**
     * 回调验证配置
     * @param wxVerifyIn
     * @return
     * @throws AesException
     */
    @ResponseBody
    @GetMapping("/receive")
    public String receiveGet(WXVerifyIn wxVerifyIn) throws AesException {

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
     * 回调验证配置  接收事件消息
     * @param wxVerifyIn
     * @return
     * @throws AesException
     */
    @ResponseBody
    @PostMapping(value = "/receive",consumes = MediaType.TEXT_XML_VALUE)
    public String receivePost(WXVerifyIn wxVerifyIn, HttpServletRequest request, HttpServletResponse response) throws AesException, UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        /**
         * 1.对msg_signature进行校验
         * 2.解密Encrypt，得到明文的消息结构体（消息结构体后面章节会详说）
         * 3.如果需要被动回复消息，构造被动响应包
         * 4.正确响应本次请求
         */

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        try {
            InputStream inputStream = request.getInputStream();
            //得到主动推送消息
            String postData = IOUtils.toString(inputStream, "UTF-8");
            String result = wxcpt.DecryptMsg(wxVerifyIn, postData);
            // 验证URL成功，将sEchoStr返回
            System.out.println(result);
            return result;
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            return null;
        }

    }

}
