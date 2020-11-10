package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveLocation extends Receive implements Serializable {

    private static final long serialVersionUID = -1145568527361388070L;
    /**
     * 地理位置纬度
     */
    @JsonProperty(value = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JsonProperty(value = "Location_Y")
    private String location_Y;

    /**
     * 地图缩放大小
     */
    @JsonProperty(value = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @JsonProperty(value = "Label")
    private String label;

    /**
     * app类型，在企业微信固定返回wxwork，在微信不返回该字段
     */
    @JsonProperty(value = "AppType")
    private String appType;

}
