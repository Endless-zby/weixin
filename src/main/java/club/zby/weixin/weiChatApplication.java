package club.zby.weixin;

import club.zby.weixin.listener.MyCustomMetricsListener;
import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;


@Slf4j
@EnableAsync
//@EnableScheduling
@SpringBootApplication
public class weiChatApplication extends WebMvcConfigurationSupport {

//    @Bean
//    public ApnsClient apnsClient() {
//        try {
//            return new ApnsClientBuilder()
//                    .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
//                    .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new ClassPathResource("AuthKey_LH4T9V5U4R_5U8LBRXG3A.p8").getFile(),
//                            "5U8LBRXG3A", "LH4T9V5U4R"))
//                    .setMetricsListener(new MyCustomMetricsListener())  // 搞个监听器 收集指标信息
//                    .build();
//        }catch (Exception e){
//            log.error("weiChatApplication init APNs error",e);
//        }
//        log.error("IOS APNs推送初始化失败！ APNs不可用！");
//        return null;
//    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(weiChatApplication.class, args);
        log();
//        ApnsClient apnsClient = run.getBean(ApnsClient.class);
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
//                    apnsClient.close(); // 关闭apns客户端
                    log.error("weiChatApplication stop");
                }
        ));
    }

    public static void log(){

        log.info("系统启动 - " + LocalDateTime.now() );

    }


}
