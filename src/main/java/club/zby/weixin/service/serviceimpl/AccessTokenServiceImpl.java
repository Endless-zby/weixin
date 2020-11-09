package club.zby.weixin.service.serviceimpl;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.UrlTemplateEnum;
import club.zby.weixin.service.AccessTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 2:00
 */

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${corpid}")
    private String sCorpID; //企业ID
    @Value("${secret}")
    private String secret;  //应用的凭证密钥

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisTemplate<String,String> redisTemplate;


    public String getAccessToken() throws Exception {
        try {
            // redis 中搜索AccessToken_VPN
            BoundValueOperations<String, String> accessToken_vpn = redisTemplate.boundValueOps("AccessToken_VPN");
            String accessToken = accessToken_vpn.get();
            if(StringUtils.isNotEmpty(accessToken)){
                return accessToken;
            }
            HashMap<String, String> var = new HashMap<>();
            var.put("corpid",sCorpID);
            var.put("corpsecret",secret);
            ResponseEntity<ApiRespones> responseEntity = restTemplate.getForEntity(UrlTemplateEnum.GET_ACCESS_TOKEN.getUrl(), ApiRespones.class,var);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                ApiRespones apiRespones = responseEntity.getBody();
                System.out.println(apiRespones);
                // 此处保存获取的AccessToken
                assert apiRespones != null : Objects.requireNonNull(responseEntity.getBody()).getErrmsg();
                accessToken_vpn.set(apiRespones.getAccess_token(),apiRespones.getExpires_in() - 60, TimeUnit.SECONDS);
                return apiRespones.getAccess_token();
            }
            return null;

        }catch (Exception e){
            return null;
        }
    }

}
