package club.zby.weixin.controller.test;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
public class SignLocation implements Serializable {
    private static final long serialVersionUID = 4506866374788596494L;

    /**
     * 签名所在的合同页数
     */
    @NotNull(message = "签名所在的合同页数不能为空")
    private String signOnPage;

    /**
     * 签名域的左下角X轴坐标值
     */
    private String signLocationLBX;

    /**
     * 签名域的左下角Y轴坐标值
     */
    private String signLocationLBY;
}
