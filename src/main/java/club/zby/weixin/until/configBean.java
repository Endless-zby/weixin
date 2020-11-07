package club.zby.weixin.until;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@ConfigurationProperties
@Configuration
public class configBean {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public RedisTemplate<String,String> redisTemplate(){
//        return new RedisTemplate<>();
//    }

}
