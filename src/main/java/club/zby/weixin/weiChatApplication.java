package club.zby.weixin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDateTime;


@Slf4j
@EnableAsync
//@EnableScheduling
@SpringBootApplication
public class weiChatApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(weiChatApplication.class, args);
        log();
    }

    public static void log(){

        log.info("系统启动 - " + LocalDateTime.now() );

    }


}
