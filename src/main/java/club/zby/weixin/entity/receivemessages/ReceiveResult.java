package club.zby.weixin.entity.receivemessages;

import club.zby.weixin.entity.MsgType;
import club.zby.weixin.until.XML;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

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
     * 文本消息
     */
    @JSONField(name = "Content")
    private String content;
    /**
     * 图片链接
     */
    @JSONField(name = "PicUrl")
    private String picUrl;
    /**
     * 图片媒体文件id
     */
    @JSONField(name = "MediaId")
    private String mediaId;
    /**
     * image类型大标签
     */
    @JSONField(name = "Image")
    private String image;
    /**
     * 语音类型大标签
     */
    @JSONField(name = "Voice")
    private String voice;

    /**
     * 视频类型大标签
     */
    @JSONField(name = "Video")
    private String video;

    /**
     * 图文类型大标签
     */
    @JSONField(name = "Articles")
    private String articles;

    /**
     * 图文类型大标签
     */
    @JSONField(name = "ArticleCount")
    private int articleCount;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Articles{
        /**
         * 标题，不超过128个字节，超过会自动截断
         */
        private String title;
        /**
         * 描述，不超过512个字节，超过会自动截断
         */
        private String description;
        /**
         * 点击后跳转的链接。
         */
        private String picurl;
        /**
         * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080。
         */
        private String url;
    }


    /**
     * 文本消息模板
     * @param content
     * @return
     * @throws Exception
     */
    public String textTemplate(String content){
        this.content = content;
        this.msgType = MsgType.text.getType();
        ReceiveResult result = textReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, this.content);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    /**
     * 图片消息模板
     * @param mediaId
     * @return
     * @throws Exception
     */
    public String imageTemplate(String mediaId){
        this.mediaId = mediaId;
        this.msgType = MsgType.image.getType();
        ReceiveResult result = mediaReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, this.mediaId);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    /**
     * 语音消息模板
     * @param mediaId
     * @return
     * @throws Exception
     */
    public String voiceTemplate(String mediaId) throws Exception{
        this.mediaId = mediaId;
        this.msgType = MsgType.voice.getType();
        ReceiveResult result = mediaReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, this.mediaId);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    /**
     * 视频消息模板
     * @param mediaId
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public String videoTemplate(String mediaId,String title,String description){
        this.mediaId = mediaId;
        this.msgType = MsgType.video.getType();
        ReceiveResult result = videoReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, mediaId, title, description);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    /**
     * 图文消息模板
     * @param articles
     * @return
     * @throws Exception
     */
    public String newsTemplate(List<Articles> articles) {
        this.msgType = "news";
        ReceiveResult result = newsReceiveResult(this.toUserName, this.fromUserName, this.createTime, this.msgType, articles);
        JSONObject jsonObject = new JSONObject(JSON.toJSONString(result));
        return "<xml>\n" + XML.toString(jsonObject) + "\n</xml>";
    }

    public ReceiveResult mediaReceiveResult(String toUserName, String fromUserName, String createTime, String msgType, String mediaId) {
        ReceiveResult receiveResult = new ReceiveResult();
        receiveResult.toUserName = "<![CDATA[" + fromUserName + "]]>";    //交换下fromUserName 和 toUserName
        receiveResult.fromUserName = "<![CDATA[" + toUserName + "]]>";
        receiveResult.createTime = createTime;
        receiveResult.msgType = "<![CDATA[" + msgType + "]]>";
        receiveResult.content = "<![CDATA[" + content + "]]>";
        if(MsgType.image.getType().equals(msgType)){
            receiveResult.image = "<MediaId><![CDATA[" + mediaId + "]]> </MediaId>"; //图片类型消息
        }else if(MsgType.voice.getType().equals(msgType)){
            receiveResult.voice = "<MediaId><![CDATA[" + mediaId + "]]> </MediaId>"; //图片类型消息
        }
        return receiveResult;
    }
    public ReceiveResult textReceiveResult(String toUserName, String fromUserName, String createTime, String msgType, String content){
        ReceiveResult receiveResult = new ReceiveResult();
        receiveResult.toUserName = "<![CDATA[" + fromUserName + "]]>";    //交换下fromUserName 和 toUserName
        receiveResult.fromUserName = "<![CDATA[" + toUserName + "]]>";
        receiveResult.createTime = createTime;
        receiveResult.msgType = "<![CDATA[" + msgType + "]]>";
        receiveResult.content = "<![CDATA[" + content + "]]>";
        return receiveResult;
    }
    public ReceiveResult videoReceiveResult(String toUserName, String fromUserName, String createTime, String msgType, String mediaId, String title,String description){
        ReceiveResult receiveResult = new ReceiveResult();
        receiveResult.toUserName = "<![CDATA[" + fromUserName + "]]>";    //交换下fromUserName 和 toUserName
        receiveResult.fromUserName = "<![CDATA[" + toUserName + "]]>";
        receiveResult.createTime = createTime;
        receiveResult.msgType = "<![CDATA[" + msgType + "]]>";
        receiveResult.video =
                "<Video>" +
                "<MediaId><![CDATA[" + mediaId + "]]></MediaId>" +
                "<Title><![CDATA[" + title + "]]></Title>" +
                "<Description><![CDATA[" + description + "]]></Description>" +
                "</Video>";
        return receiveResult;
    }
    public ReceiveResult newsReceiveResult(String toUserName, String fromUserName, String createTime, String msgType, List<Articles> articles){
        ReceiveResult receiveResult = new ReceiveResult();
        receiveResult.toUserName = "<![CDATA[" + fromUserName + "]]>";    //交换下fromUserName 和 toUserName
        receiveResult.fromUserName = "<![CDATA[" + toUserName + "]]>";
        receiveResult.createTime = createTime;
        receiveResult.msgType = "<![CDATA[" + msgType + "]]>";
        StringBuilder buffer = new StringBuilder();
        for (Articles item : articles) {
            buffer.append("<item>" + "<Title><![CDATA[").append(item.getTitle()).append("]]></Title>").append("<Description><![CDATA[").append(item.getDescription()).append("]]></Description>").append("<PicUrl><![CDATA[").append(item.getPicurl()).append("]]></PicUrl>").append("<Url><![CDATA[").append(item.getUrl()).append("]]></Url>").append("</item>");
        }
        receiveResult.articles = buffer.toString();
        receiveResult.articleCount = articles.size();
        return receiveResult;
    }
}
