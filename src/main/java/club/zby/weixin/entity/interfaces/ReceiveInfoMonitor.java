package club.zby.weixin.entity.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 赵博雅
 * @date 2021/3/24 17:45
 */
//Receive的切面类
@Aspect
@Slf4j
@Component
//conditional.logAop 控制Component是否生效
@ConditionalOnProperty(prefix = "conditional",name = "logAop",havingValue = "true")
public class ReceiveInfoMonitor {

    @Before(value = "@annotation(club.zby.weixin.entity.interfaces.ReceiveInfo)")
    public void beforeRetuning(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        String name = joinPoint.getSignature().getName();
        log.info("请求方法:{}",name);
        log.info("接收到请求的参数:{}", Arrays.toString(args));
    }

    @AfterReturning(value = "@annotation(club.zby.weixin.entity.interfaces.ReceiveInfo)",returning = "result")
    public void afterRetuning(JoinPoint joinPoint , Object result/*注解标注的方法返回值*/){

        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        Method method = ms.getMethod();
        boolean flag = method.getAnnotation(ReceiveInfo.class).flag();
        String message = method.getAnnotation(ReceiveInfo.class).message();
        log.info("返回数据:{}",result.toString());
    }
}
