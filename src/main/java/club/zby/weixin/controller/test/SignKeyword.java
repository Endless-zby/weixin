package club.zby.weixin.controller.test;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
public class SignKeyword implements Serializable {
    private static final long serialVersionUID = -3366779571418478719L;

    /**
     * 根据关键字创建签名
     */
    @NotNull(message = "关键字不能为空")
    private String keyword;

    /**
     * X轴偏移坐标
     */
    private String offsetCoordX;

    /**
     * Y轴偏移坐标
     */
    private String offsetCoordY;

    /**
     * 页码
     */
    private String pageNo;
}
