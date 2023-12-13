package club.zby.weixin.service;

import com.tencentcloudapi.common.AbstractModel;
import org.springframework.stereotype.Service;

@Service
public interface FanYiService {

    String  textTranslate(String sourceText, String source, String target) throws Exception;

}
