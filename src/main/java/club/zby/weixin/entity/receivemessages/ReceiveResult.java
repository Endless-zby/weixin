package club.zby.weixin.entity.receivemessages;

import club.zby.weixin.until.XML;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/14 12:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveResult implements Serializable {
    private static final long serialVersionUID = -7997890732800687609L;

    /**
     * 企业微信CorpID
     */
    @JSONField(name = "ToUserName")
    private String toUserName;
    /**
     * 成员UserID
     */
    @JSONField(name = "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间（整型）
     */
    @JSONField(name = "CreateTime")
    private String createTime;
    /**
     * 消息类型
     */
    @JSONField(name = "MsgType")
    private String msgType;
    /**
     * 文本消息内容
     */
    @JSONField(name = "Content")
    private String content;
    /**
     * 图片链接
     */
    @JSONField(name = "PicUrl")
    private String picUrl;

    public String textTemplate(String content){
        this.content = content;
        this.msgType = "text";
        ReceiveResult result = new ReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, this.content);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    public ReceiveResult(String toUserName, String fromUserName, String createTime, String msgType, String content) {
        this.toUserName = "<![CDATA[" + fromUserName + "]]>";    //交换下fromUserName 和 toUserName
        this.fromUserName = "<![CDATA[" + toUserName + "]]>";
        this.createTime = createTime;
        this.msgType = "<![CDATA[" + msgType + "]]>";
        this.content = "<![CDATA[" + content + "]]>";
    }


}
