package club.zby.weixin.entity;

import lombok.Data;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/28 17:51
 */
@Data
public class ReceiveData {

    /**
     * 服务端接收到的来自客户端的数据
     */
    String postData;
    /**
     * 接收到的数据类型
     */
    String type;

}
