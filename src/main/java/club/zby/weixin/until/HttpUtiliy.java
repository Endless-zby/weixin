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
     * body
     * @param URL
     * @param mediaType
     * @param body
     * @param responseType
     * @return
     */
    public ResponseEntity<T> postForEntity(String URL, MediaType mediaType, Map<String,Object> body,Class<T> responseType){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(body), httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(URL, entity, responseType);
        return responseEntity;
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(body), httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(URL, entity, responseType,uriVariables);
        return responseEntity;
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(URL, entity, responseType);
        return responseEntity;
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(URL, entity, responseType,uriVariables);
        return responseEntity;
    }

}
