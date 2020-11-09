package club.zby.weixin.entity.receivemessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/9 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveText {

    /**
     * 企业微信CorpID
     */
    private String ToUserName;
    /**
     * 成员UserID
     */
    private String FromUserName;
    /**
     * 消息创建时间（整型）
     */
    private String CreateTime;
    /**
     * 消息类型，此时固定为：text
     */
    private String MsgType;
    /**
     * 文本消息内容
     */
    private String Content;
    /**
     * 消息id，64位整型
     */
    private String MsgId;
    /**
     * 企业应用的id，整型。可在应用的设置页面查看
     */
    private String AgentID;

}
