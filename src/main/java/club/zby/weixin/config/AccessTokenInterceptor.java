package club.zby.weixin.config;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.SecretData;
import club.zby.weixin.entity.UrlTemplateEnum;
import club.zby.weixin.until.HttpUtiliy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 自动装载accessToken redis中的失效后重新发送请求获取，并写入redis
 *
 * @author 赵博雅
 * @date 2020/11/9 17:22
 */
@Configuration
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Resource
    private HttpUtiliy httpUtiliy;
    @Resource
    private SecretData secretData;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // redis 中搜索AccessToken_VPN
        BoundValueOperations<String, String> accessToken_vpn = redisTemplate.boundValueOps("AccessToken_VPN");
        String accessToken = accessToken_vpn.get();
        if(StringUtils.isNotEmpty(accessToken)){
            request.setAttribute("accessToken",accessToken);
            return true;
        }
        HashMap<String, String> var = new HashMap<>();
        var.put("corpid",secretData.getCorpId());
        var.put("corpsecret",secretData.getSecret());
        ResponseEntity<ApiRespones> responseEntity = httpUtiliy.getForEntity(UrlTemplateEnum.GET_ACCESS_TOKEN.getUrl(), ApiRespones.class,var);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            ApiRespones apiRespones = responseEntity.getBody();
            // 此处保存获取的AccessToken
            assert apiRespones != null : Objects.requireNonNull(responseEntity.getBody()).getErrmsg();
            accessToken_vpn.set(apiRespones.getAccess_token(),apiRespones.getExpires_in() - 60, TimeUnit.SECONDS);
            request.setAttribute("accessToken",accessToken);
            return true;
        }
        return false;
    }
}
