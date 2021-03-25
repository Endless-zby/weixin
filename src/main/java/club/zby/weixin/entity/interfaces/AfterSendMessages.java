package club.zby.weixin.entity.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 赵博雅
 * @date 2021/3/25 14:18
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AfterSendMessages {

    /**
     * 当messages为空时，发送该请求的入参和出参，messages不为空时，返回messages
     * @return
     */
    String messages() default "";

    boolean isSendEmail() default false;    //开启发送邮件功能

    String[] toEmail() default {"2220624782@qq.com"};        //发邮件给这个人

    String topic() default "idea激活码";     //主题

}
