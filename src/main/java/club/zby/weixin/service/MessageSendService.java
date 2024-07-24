package club.zby.weixin.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageSendService {

    void send(String title, String message, String clientToken);

}
