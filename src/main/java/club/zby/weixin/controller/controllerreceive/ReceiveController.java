package club.zby.weixin.controller.controllerreceive;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.WXVerifyIn;
import club.zby.weixin.entity.receivemessages.ReceiveText;
import club.zby.weixin.factory.ReceiveFactory;
import club.zby.weixin.service.ReceiveService;
import club.zby.weixin.until.aesconfig.AesException;
import club.zby.weixin.until.aesconfig.WXBizMsgCrypt;
import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    private ReceiveFactory receiveFactory;

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
     * 回调验证配置  接收事件消息  回复消息
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/receive",consumes = MediaType.TEXT_XML_VALUE)
    public String receivePost(HttpServletRequest request) throws AesException {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        String receive = (String)request.getAttribute("receive");
        String type = (String)request.getAttribute("type");
        ReceiveService receiveFactory = this.receiveFactory.getReceiveFactory(type);
        String msg = "<xml>\n" +
                "   <ToUserName><![CDATA[ZhaoBoYa]]></ToUserName>\n" +
                "   <FromUserName><![CDATA[ww8783f3dbd5f49266]]></FromUserName> \n" +
                "   <CreateTime>1348831860</CreateTime>\n" +
                "   <MsgType><![CDATA[text]]></MsgType>\n" +
                "   <Content><![CDATA[成功]]></Content>\n" +
                "</xml>";
        if(receiveFactory == null){
            msg = "不支持此消息类型";
        }
        String s = receiveFactory.replyXmlInfo(receive);
        String result = wxcpt.EncryptMsg(msg, "1111", "111");
        return result;
    }

}
