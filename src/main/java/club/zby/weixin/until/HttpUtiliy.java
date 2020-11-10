package club.zby.weixin.until;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 15:00
 *
 * 封装更适合本业务线的RestTemplate工具
 */
@Configuration
public class HttpUtiliy<T>{

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @param URL
     * @param responseType
     * @param uriVariables
     * @return
     */
    public ResponseEntity<T> getForEntity(String URL, Class<T> responseType,Map<String, ?> uriVariables){
        return restTemplate.getForEntity(URL, responseType, uriVariables);
    }

    /**
     *
     * @param URL
     * @param responseType
     * @return
     */
    public ResponseEntity<T> getForEntity(String URL, Class<T> responseType){
        return restTemplate.getForEntity(URL, responseType);
    }

    /**
     *
     * @param URL
     * @param responseType
     * @param uriVariables
     * @return
     */
    public ResponseEntity<T> getForEntity(String URL, Class<T> responseType, Object... uriVariables){
        return restTemplate.getForEntity(URL, responseType,uriVariables);
    }


    /**
     * body
     * @param URL
     * @param mediaType
     * @param body
     * @param responseType
     * @return
     */
    public ResponseEntity<T> postForEntity(String URL, MediaType mediaType, Map<String,Object> body,Class<T> responseType){
        HttpEntity<String> entity = getEntity(mediaType, JSON.toJSONString(body));
        return restTemplate.postForEntity(URL, entity, responseType);
    }

    /**
     *
     * @param URL
     * @param mediaType
     * @param body
     * @param responseType
     * @param uriVariables
     * @return
     */
    public ResponseEntity<T> postForEntity(String URL, MediaType mediaType, Map<String,Object> body,Class<T> responseType,Map<String, ?> uriVariables){
        HttpEntity<String> entity = getEntity(mediaType, JSON.toJSONString(body));
        return restTemplate.postForEntity(URL, entity, responseType,uriVariables);
    }

    /**
     *
     * @param URL
     * @param mediaType
     * @param body
     * @param responseType
     * @return
     */
    public ResponseEntity<T> postForEntity(String URL, MediaType mediaType, String body,Class<T> responseType){
        HttpEntity<String> entity = getEntity(mediaType, body);
        return restTemplate.postForEntity(URL, entity, responseType);
    }

    /**
     *
     * @param URL
     * @param mediaType
     * @param body
     * @param responseType
     * @param uriVariables
     * @return
     */
    public ResponseEntity<T> postForEntity(String URL, MediaType mediaType, String body,Class<T> responseType,Map<String, ?> uriVariables){
        HttpEntity<String> entity = getEntity(mediaType, body);
        return restTemplate.postForEntity(URL, entity, responseType,uriVariables);
    }


    /**
     *
     * @param mediaType
     * @param body
     * @return
     */
    public HttpEntity<String> getEntity(MediaType mediaType,String body){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        return new HttpEntity<>(body, httpHeaders);
    }


}
