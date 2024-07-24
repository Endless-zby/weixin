package club.zby.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiChatRespones<T> implements Serializable {
    private static final long serialVersionUID = -8160886787235984955L;

    public static final int OK = 0;
    public static final int ERROR = -1;

    private int code;
    /**
     * 返回码提示语
     */
    private String errMessage;

    private T data;

    public static <T> WeiChatRespones<T> isSuccess(){
        return new WeiChatRespones<>(OK, "success", null);
    }

    public static <T> WeiChatRespones<T> isSuccess(T data){
        return new WeiChatRespones<>(OK, "success", data);
    }
    public static <T> WeiChatRespones<T> isSuccess(int code, String errMessage, T data){
        return new WeiChatRespones<>(code, errMessage, data);
    }

    public static <T> WeiChatRespones<T> isFail(String errMessage){
        return new WeiChatRespones<>(ERROR, errMessage, null);
    }
    public static <T> WeiChatRespones<T> isFail(int code, String errMessage){
        return new WeiChatRespones<>(code, errMessage, null);
    }

}
