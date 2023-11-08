package club.zby.weixin.until;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */
@Setter
@Getter
@ConfigurationProperties
@Configuration
public class configBean {



    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
