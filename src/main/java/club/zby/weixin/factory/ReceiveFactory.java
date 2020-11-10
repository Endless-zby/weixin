package club.zby.weixin.factory;

import club.zby.weixin.entity.MsgType;
import club.zby.weixin.service.ReceiveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author 赵博雅
 * @date 2020/11/10 15:37
 */
@Component
public class ReceiveFactory {
    /**
     * 获取对应消息类型的实例
     * @param type
     * @return
     */
    public ReceiveService getReceiveFactory(String type) {
        String className = MsgType.valueOf(type).getClassName();

        if(StringUtils.isEmpty(className)){
            return null;
        }

        try {
            return (ReceiveService) Class.forName(className).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
