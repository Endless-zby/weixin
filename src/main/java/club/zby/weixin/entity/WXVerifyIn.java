package club.zby.weixin.entity;

import lombok.Data;

/**
 * 用于企业微信回调
 */
@Data
public class WXVerifyIn {


    /**
     * 企业微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体
     */
    private String msg_signature;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 加密的字符串。需要解密得到消息内容明文，解密后有random、msg_len、msg、CorpID四个字段，其中msg即为消息内容明文
     */
    private String echostr;

}
