package com.px.service;

import com.px.annotation.MyTransaction;
import com.px.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    @MyTransaction(isEnd = true)
    @Transactional
    public void test() {
        demoDao.insert("server2");
    }
}
