package com.px.web;

import com.px.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server2")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping(value = "test")
    public void test() {
        demoService.test();
    }
}
