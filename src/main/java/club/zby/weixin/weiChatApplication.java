package club.zby.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class weiChatApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(weiChatApplication.class, args);
    }

}
