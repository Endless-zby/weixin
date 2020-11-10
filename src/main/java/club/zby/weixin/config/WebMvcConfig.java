package club.zby.weixin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

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

    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor);

        registry.addInterceptor(dealWithReceiveInterceptor)
                .addPathPatterns("/receive");


        super.addInterceptors(registry);
    }
}
