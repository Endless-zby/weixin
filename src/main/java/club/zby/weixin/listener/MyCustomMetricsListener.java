package club.zby.weixin.listener;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientMetricsListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCustomMetricsListener implements ApnsClientMetricsListener {
    @Override
    public void handleWriteFailure(ApnsClient apnsClient, long l) {
        log.info("MyCustomMetricsListener handleWriteFailure {}", l);
    }

    @Override
    public void handleNotificationSent(ApnsClient apnsClient, long l) {
        log.info("MyCustomMetricsListener handleNotificationSent {}", l);
    }

    @Override
    public void handleNotificationAccepted(ApnsClient apnsClient, long l) {
        log.info("MyCustomMetricsListener handleNotificationAccepted {}", l);
    }

    @Override
    public void handleNotificationRejected(ApnsClient apnsClient, long l) {
        log.info("MyCustomMetricsListener handleNotificationRejected {}", l);
    }

    @Override
    public void handleConnectionAdded(ApnsClient apnsClient) {
        log.info("MyCustomMetricsListener handleConnectionAdded");
    }

    @Override
    public void handleConnectionRemoved(ApnsClient apnsClient) {
        log.info("MyCustomMetricsListener handleConnectionRemoved");
    }

    @Override
    public void handleConnectionCreationFailed(ApnsClient apnsClient) {
        log.info("MyCustomMetricsListener handleConnectionCreationFailed");
    }
}
