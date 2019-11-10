package com.px.service;

import com.px.config.R;
import com.px.dao.Demodao;
import io.netty.handler.codec.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {

    @Autowired
    private Demodao demoDao;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public R test() {
        demoDao.insert("server1");
        // HttpUtil.post("http://localhost:8082/server2/test");
        // int i = 1/0;
        Object o = restTemplate.postForObject("http://localhost:8082/server2/test", null, Object.class);
        return R.success("", o);
    }
}
