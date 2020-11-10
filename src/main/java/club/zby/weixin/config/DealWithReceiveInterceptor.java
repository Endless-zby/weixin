package club.zby.weixin.config;

import club.zby.weixin.until.aesconfig.WXBizMsgCrypt;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author 赵博雅
 * @date 2020/11/10 11:09
 */
@Configuration
public class DealWithReceiveInterceptor extends HandlerInterceptorAdapter {

    @Value("${token}")
    private String sToken;
    @Value("${EncodingAESKey}")
    private String sEncodingAESKey;
    @Value("${corpid}")
    private String sCorpID; //企业ID

    /**
     * 1.对msg_signature进行校验
     * 2.解密Encrypt，得到明文的消息结构体（消息结构体后面章节会详说）
     * 3.如果需要被动回复消息，构造被动响应包
     * 4.正确响应本次请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        try {
            InputStream inputStream = request.getInputStream();
            //得到主动推送消息
            String postData = IOUtils.toString(inputStream, "UTF-8");
            String timestamp = request.getParameter("timestamp");
            String msg_signature = request.getParameter("msg_signature");
            String nonce = request.getParameter("nonce");
            String result = wxcpt.DecryptMsg(msg_signature,timestamp,nonce,postData);

            JSONObject jsonObject = XML.toJSONObject(result);

            if(jsonObject.isNull("xml")){
                return true;
            }
            JSONObject xml = jsonObject.getJSONObject("xml");

            if(xml.isNull("MsgType")){
                return true;
            }
            String receive = xml.toString();
            request.setAttribute("receive",receive);
            request.setAttribute("type",xml.get("MsgType"));
            return true;
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            return true;
        }

    }

}
