package club.zby.weixin.controller.test;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;

@Data
@Validated
public class SignDataInfo implements Serializable {

    private static final long serialVersionUID = 3431426930204407120L;

    /**
     * userID
     */
    private String userId;

    /**
     * 添加印章返回的印章ID认印章
     */
    private String sealId;

    /**
     * 没有印章图片或者印章ID时使用开户默认印章
     */
    private String sealImage;

    /**
     * 关键字签名
     */
    private List<SignKeyword> signKeywords;

    /**
     * 坐标签名
     */
    private List<SignLocation> signLocations;
}
