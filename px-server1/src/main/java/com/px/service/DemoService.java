package com.px.service;

import com.px.annotation.MyTransaction;
import com.px.config.R;
import com.px.dao.Demodao;
import com.px.task.HttpUtil;
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

    @MyTransaction(isStart = true)
    @Transactional
    public R test() {
        // demoDao.insert("server1");
        Object post = HttpUtil.post("http://localhost:8082/server2/test");
        // Object o = restTemplate.postForObject("http://localhost:8082/server2/test", null, Object.class);
        // int i = 1/0;
        return R.success("", null);
    }
}
