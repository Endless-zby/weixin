package club.zby.weixin.entity.receivemessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 赵博雅
 * @date 2020/11/10 18:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventClick extends Receive implements Serializable {
    private static final long serialVersionUID = -1689316726774523297L;

    /**
     * 事件类型
     */
    @JsonProperty(value = "Event")
    private String event;

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JsonProperty(value = "EventKey")
    private String eventKey;


}
