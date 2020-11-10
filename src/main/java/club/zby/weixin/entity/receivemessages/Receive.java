package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receive implements Serializable {
    private static final long serialVersionUID = -4403317188382330260L;

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
     * 消息id，64位整型
     */
    @ToString.Exclude
    @JsonProperty(value = "MsgId")
    private String msgId;
    /**
     * 企业应用的id，整型。可在应用的设置页面查看
     */
    @JsonProperty(value = "AgentID")
    private String agentID;

}
