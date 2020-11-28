package club.zby.weixin.config;

import club.zby.weixin.entity.interfaces.ReceiveInfoMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 赵博雅
 * @date 2020/11/10 11:03
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private AccessTokenInterceptor accessTokenInterceptor;
    @Resource
    private DealWithReceiveInterceptor dealWithReceiveInterceptor;
    @Resource
    private ReceiveInfoMethodArgumentResolver receiveInfoMethodArgumentResolver;


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor);

        registry.addInterceptor(dealWithReceiveInterceptor)
                .addPathPatterns("/receive");


        super.addInterceptors(registry);
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(receiveInfoMethodArgumentResolver);


    }

}
