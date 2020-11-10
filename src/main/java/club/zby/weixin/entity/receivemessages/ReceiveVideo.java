package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveVideo extends Receive implements Serializable {

    private static final long serialVersionUID = -8002464032697774154L;
    /**
     * 视频媒体文件id，可以调用获取媒体文件接口拉取数据，仅三天内有效
     */
    @JsonProperty(value = "MediaId")
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用获取媒体文件接口拉取数据，仅三天内有效
     */
    @JsonProperty(value = "ThumbMediaId")
    private String thumbMediaId;

}
