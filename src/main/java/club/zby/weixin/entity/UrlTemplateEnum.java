package club.zby.weixin.entity;

/**
 * @author 赵博雅
 * @date 2020/11/9 16:04
 */

public enum UrlTemplateEnum {
    /**
     * # 获取登录凭证 accessToken 企业微信所有API接口都依赖accessToken来鉴权调用者身份
     * GET_ACCESS_TOKEN: https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}
     *
     * # 创建 群聊会话
     * POST_APPCHAT_CREATE: https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token={accesstoken}
     *
     * # 获取 群聊会话 只允许企业自建应用调用 chatid所代表的群必须是该应用所创建
     * GET_APPCHAT_GET: https://qyapi.weixin.qq.com/cgi-bin/appchat/get?access_token={accesstoken}&chatid={chatid}
     *
     * # 应用 推送消息 应用支持推送文本、图片、视频、文件、图文等类型。
     * POST_APPCHAT_SEND: https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token={accesstoken}
     *
     * # 群机器人 自动发布信息
     * POST_WEBHOOK_SEND: https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=4db83b3c-359b-48e2-9b88-7bfff9a58e99
     *
     * # 菜单 创建菜单
     * POST_MENU_CREATE: https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token={access_token}&agentid={agentid}
     *
     * #菜单获取菜单
     * GET_MENU_GET: https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token={access_token}&agentid={agentid}
     */


    GET_ACCESS_TOKEN("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}","获取登录凭证"),

    POST_APPCHAT_CREATE("https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token={accesstoken}","创建_群聊会话"),

    GET_APPCHAT_GET("https://qyapi.weixin.qq.com/cgi-bin/appchat/get?access_token={accesstoken}&chatid={chatid}","获取_群聊会话"),

    POST_APPCHAT_SEND("https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token={accesstoken}","应用_推送消息"),

    POST_WEBHOOK_SEND("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=4db83b3c-359b-48e2-9b88-7bfff9a58e99","群机器人_自动发布信息"),

    POST_MENU_CREATE("https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token={access_token}&agentid={agentid}","菜单_创建菜单"),

    GET_MENU_GET("https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token={access_token}&agentid={agentid}","菜单_获取菜单");

    private String url;
    private String desc;

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    UrlTemplateEnum(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }



}
