package club.zby.weixin.service.serviceimpl.messagesendserviceimpl;

import club.zby.weixin.service.MessageSendService;
import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * IOS消息推送
 */
@Slf4j
@Component
public class APNsServiceImpl implements MessageSendService {

//    static ApnsClient apnsClient;
//
//    static {
//        try {
//            apnsClient = new ApnsClientBuilder()
//                    .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
//                    .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new ClassPathResource("AuthKey_LH4T9V5U4R_5U8LBRXG3A.p8").getFile(),
//                            "5U8LBRXG3A", "LH4T9V5U4R"))
//                    .build();
//        }catch (Exception e){
//            log.error("init apnsClient error",e);
//            log.error("IOS APNs推送初始化失败！ APNs不可用！");
//        }
//
//    }

    @Override
    public void send(String message, String clientToken) {


        try {
            ApnsClient apnsClient = new ApnsClientBuilder()
                    .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                    .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new ClassPathResource("AuthKey_LH4T9V5U4R_5U8LBRXG3A.p8").getFile(),
                            "5U8LBRXG3A", "LH4T9V5U4R"))
                    .build();



            final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
            payloadBuilder.setAlertBody(message);
            payloadBuilder.setAlertTitle("测试");
            clientToken = "8a5c16959ae103b643bfe32c4af80b3b1d55f07f732f5ee600a67a6cf53d9aab";
            final String payload = payloadBuilder.build();
            final String token = TokenUtil.sanitizeTokenString(clientToken);

            SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, "me.fin.bark", payload);
            PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient.sendNotification(pushNotification);
            // 发送通知的过程是异步的，客户端会立即返回CompletableFuture（子类PushNotificationFuture）
            // 虽然异步进行但是Future依旧会获取到结果
//            1.网关接受通知并将尝试将其传送到目标设备。
//            2.网关拒绝通知；这应被视为永久性故障，不应再次发送通知。此外，APNs 网关可能会指示目标令牌无效的时间戳。如果发生这种情况，您应该停止尝试向该令牌发送任何通知，除非该令牌自该时间戳以来已重新注册。
//            3.失败CompletableFuture并出现异常。这通常应被视为暂时失败，调用者应在问题解决后再次尝试发送通知。

            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();

            if (pushNotificationResponse.isAccepted()) {
                System.out.println("Push notification accepted by APNs gateway.");
            } else {
                System.out.println("Notification rejected by the APNs gateway: " +
                        pushNotificationResponse.getRejectionReason());

                pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
                    System.out.println("\t…and the token is invalid as of " + timestamp);
                });
            }
        } catch (Exception e) {
            System.err.println("Failed to send push notification.");
            e.printStackTrace();
        }
    }
}
