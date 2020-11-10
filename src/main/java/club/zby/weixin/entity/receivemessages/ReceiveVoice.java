package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 14:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveVoice extends Receive implements Serializable {

    private static final long serialVersionUID = -2008259247322986069L;
    /**
     * 语音媒体文件id，可以调用获取媒体文件接口拉取数据，仅三天内有效
     */
    @JsonProperty(value = "MediaId")
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    @JsonProperty(value = "Format")
    private String format;

}
