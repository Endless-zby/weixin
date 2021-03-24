package club.zby.weixin.entity.interfaces;

import java.lang.annotation.*;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/28 17:27
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface ReceiveInfo {

    //从request中装载接收到的消息体和消息类型
    boolean flag() default true;

    String message() default "未接受到任何消息";

}
