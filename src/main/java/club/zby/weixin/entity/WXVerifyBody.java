package club.zby.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 15:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WXVerifyBody {
    /**
     * 企业微信的CorpID，当为第三方套件回调事件时，CorpID的内容为suiteid
     */
    private String ToUserName;
    /**
     * 接收的应用id，可在应用的设置页面获取
     */
    private String AgentID;
    /**
     * 消息结构体加密后的字符串
     */
    private String Encrypt;

}
