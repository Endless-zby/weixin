package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveImage extends Receive implements Serializable {

    private static final long serialVersionUID = -431637156742363273L;
    /**
     * 图片链接
     */
    @JsonProperty(value = "PicUrl")
    private String picUrl;

    /**
     * 图片媒体文件id，可以调用获取媒体文件接口拉取，仅三天内有效
     */
    @JsonProperty(value = "MediaId")
    private String mediaId;

    public String toSuperString(){
        return super.toString();
    }

}
