package club.zby.weixin.entity;


/**
 * @author 赵博雅
 * @date 2020/11/10 14:50
 */
public enum MsgType {

    text("text","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveTextServiceImpl","文本消息"),
    image("image","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveImageServiceImpl","图片消息"),
    voice("voice","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveVoiceServiceImpl","语音消息"),
    video("video","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveVideoServiceImpl","视频消息"),
    location("location","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveLocationServiceImpl","位置消息"),
    link("link","club.zby.weixin.service.serviceimpl.receiveserviceimpl.ReceiveLinkServiceImpl","链接消息"),


    event("event","club.zby.weixin.service.serviceimpl.eventserviceimpl.EventClickServiceImpl","事件消息");

    /**
     * 消息类型
     */
    private String type;
    /**
     * 实例
     */
    private String className;
    /**
     * 消息类型解释
     */
    private String typeDesc;

    MsgType(String type, String className,String typeDesc){
        this.className = className;
        this.type = type;
        this.typeDesc = typeDesc;
    }

    public String getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
