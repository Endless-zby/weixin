package club.zby.weixin.service.serviceimpl;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.RobotTemplate;
import club.zby.weixin.entity.UrlTemplateEnum;
import club.zby.weixin.service.RobotService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 2:00
 */

@Service
public class RobotServiceImpl implements RobotService {

    @Resource
    private RestTemplate restTemplate;



    @Override
    public ApiRespones robotToSendByMarkdown(List<RobotTemplate> robotTemplates) {
        HashMap<String, Object> variable = new HashMap<>();
        variable.put("msgtype","markdown");
        HashMap<String, Object> variablev2 = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (RobotTemplate robot: robotTemplates) {
            stringBuffer.append(robot.buildRobotInfo());
        }
        variablev2.put("content",stringBuffer);
        variable.put("markdown",variablev2);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(variable), httpHeaders);
        ResponseEntity<ApiRespones> apiResult = restTemplate.postForEntity(UrlTemplateEnum.POST_WEBHOOK_SEND.getUrl(), entity, ApiRespones.class);
        if(apiResult.getStatusCode().is2xxSuccessful()){
            return apiResult.getBody();
        }
        return new ApiRespones("发送失败").build();


    }
}
