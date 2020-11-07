package club.zby.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRespones {

    public static final int OK = 0;
    public static final int ERROR = -1;

    public ApiRespones(String errmsg){
        this.errmsg = errmsg;
        this.errcode = -1;
    }

    public ApiRespones(int errcode,String errmsg){
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    /**
     * 出错返回码，为0表示成功，非0表示调用失败
     */
    private Integer errcode;
    /**
     * 返回码提示语
     */
    private String errmsg;
    /**
     * 获取到的凭证，最长为512字节
     */
    private String access_token;
    /**
     * 凭证的有效时间（秒）
     */
    private long expires_in;
    /**
     * 群聊的唯一标志
     */
    private String chatid;


    public ApiRespones build(){
        return new ApiRespones(errcode,errmsg);
    }

    public Boolean isSuccess(){
        return this.errcode == OK;
    }

}
