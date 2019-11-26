package com.px.task;

import com.px.transaction.TransactionManage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 封装一个工具，将事务组id放在请求头里面发送给其他服务
 */
@Component
public class HttpUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    public  static Object post(String url){
        HttpHeaders header = new HttpHeaders();
        header.set("groupId", TransactionManage.getCurrent());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null, header);
        return  restTemplate.postForObject(url,httpEntity,Object.class);

    }
}
