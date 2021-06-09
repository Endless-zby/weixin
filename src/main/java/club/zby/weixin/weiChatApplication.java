package club.zby.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
public class weiChatApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(weiChatApplication.class, args);
    }
}
