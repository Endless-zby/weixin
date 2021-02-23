package club.zby.weixin.entity.interfaces;

import club.zby.weixin.entity.ReceiveData;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletRequest;
import java.util.Objects;

/**
 * 从request 中获取Receive 实例
 * @author byzhao
 * @version 1.0
 * @date 2020/11/28 17:45
 */
@Component
public class ReceiveInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {


    /**
     * 判断什么时候要执行下面的resolveArgument方法
     * 若不想自定义注解，可以直接在实现HandlerMethodArgumentResolver的supportsParameter直接返回true 这样每一个请求过来的都会分解该参数
     *
     * 当一个方法的参数含有@ReceiveInfo时执行下面的resolveArgument方法
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ReceiveInfo.class) && ReceiveData.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        final ReceiveInfo receiveInfo = methodParameter.getParameterAnnotation(ReceiveInfo.class);
        assert receiveInfo != null;
        ServletRequest servletRequest = (ServletRequest) nativeWebRequest.getNativeRequest();
        ReceiveData receiveData = new ReceiveData();
        receiveData.setPostData((String)servletRequest.getAttribute("receive"));
        receiveData.setType((String)servletRequest.getAttribute("type"));
        if (!receiveInfo.flag()) {
            throw new Exception(receiveInfo.message());
        }
        return receiveData;

    }
}
