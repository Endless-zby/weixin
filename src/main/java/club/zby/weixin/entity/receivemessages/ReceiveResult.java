package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonProperty(value = "ToUserName")
    private String toUserName;
    /**
     * 成员UserID
     */
    @JsonProperty(value = "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间（整型）
     */
    @JsonProperty(value = "CreateTime")
    private String createTime;
    /**
     * 消息类型
     */
    @JsonProperty(value = "MsgType")
    private String msgType;
    /**
     * 文本消息内容
     */
    @JsonProperty(value = "Content")
    private String content;
    /**
     * 图片链接
     */
    @JsonProperty(value = "PicUrl")
    private String picUrl;

    public void textTemplate(String content){
        this.content = content;
        this.msgType = "text";
    }


}
