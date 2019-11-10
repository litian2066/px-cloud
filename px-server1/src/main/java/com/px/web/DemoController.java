package com.px.web;

import com.px.config.R;
import com.px.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server1")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "test")
    public R test() {
        return demoService.test();
    }
}