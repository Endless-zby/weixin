package club.zby.weixin.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 赵博雅
 * @date 2020/11/9 17:22
 */
@Configuration
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // redis 中搜索AccessToken_VPN
        BoundValueOperations<String, String> accessToken_vpn = redisTemplate.boundValueOps("AccessToken_VPN");
        String accessToken = accessToken_vpn.get();
        if(StringUtils.isNotEmpty(accessToken)){
            request.setAttribute("accessToken",accessToken);
        }
        return true;
    }
}
