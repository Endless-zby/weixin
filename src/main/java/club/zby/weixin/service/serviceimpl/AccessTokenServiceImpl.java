package club.zby.weixin.service.serviceimpl;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.service.AccessTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${corpid}")
    private String sCorpID; //企业ID
    @Value("${secret}")
    private String secret;  //应用的凭证密钥
    @Value("${GET_ACCESS_TOKEN}")
    private String GET_ACCESS_TOKEN;

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
            String getTokenUrl = GET_ACCESS_TOKEN.replace("{corpid}",sCorpID).replace("{corpsecret}",secret);
            ResponseEntity<ApiRespones> responseEntity = restTemplate.getForEntity(getTokenUrl, ApiRespones.class);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                ApiRespones apiRespones = responseEntity.getBody();
                System.out.println(apiRespones);
                // 此处保存获取的AccessToken
                accessToken_vpn.set(apiRespones.getAccess_token(),apiRespones.getExpires_in() - 60, TimeUnit.SECONDS);
                return apiRespones.getAccess_token();
            }
            return null;

        }catch (Exception e){
            return null;
        }
    }

}
