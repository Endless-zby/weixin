package club.zby.weixin.entity.interfaces;

import java.lang.annotation.*;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/28 17:27
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ReceiveInfo {

    boolean required() default true;

    String message() default "未接受到任何消息";

}
