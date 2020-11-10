package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/9 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class ReceiveText extends Receive implements Serializable {

    private static final long serialVersionUID = -3128203767369877526L;

    /**
     * 文本消息内容
     */
    @JsonProperty(value = "Content")
    private String content;

}
