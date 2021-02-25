package club.zby.weixin.service;

import org.springframework.stereotype.Service;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 2:00
 */
@Service
public interface AccessTokenService {

    String getAccessToken() throws Exception;


}
