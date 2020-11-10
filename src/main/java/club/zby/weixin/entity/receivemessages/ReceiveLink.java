package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveLink extends Receive implements Serializable {

    private static final long serialVersionUID = -3180284803409393458L;
    /**
     * 标题
     */
    @JsonProperty(value = "Title")
    private String title;

    /**
     * 描述
     */
    @JsonProperty(value = "Description")
    private String description;

    /**
     * 链接跳转的url
     */
    @JsonProperty(value = "Url")
    private String url;

    /**
     * 封面缩略图的url
     */
    @JsonProperty(value = "PicUrl")
    private String picUrl;

}
