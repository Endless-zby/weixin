package club.zby.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赵博雅
 * @date 2020/11/12 10:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "applicationsecret")
public class SecretData {

    /**
     * 企业id
     */
    private String corpId;
    /**
     * 应用密钥(短信转发应用)
     */
    private String secret;
    private String token;
    /**
     * 用于消息体的加密，是AESv
     */
    private String encodingAESKey;

}
