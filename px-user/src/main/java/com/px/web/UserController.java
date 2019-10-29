package com.px.web;

//import com.px.entity.JobWanted;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.px.feign.JobWantedFeign;
import com.px.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JobWantedFeign jobWantedFeign;

    @GetMapping(value = "/test")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public R getJob() {
        return R.success("Yeah", jobWantedFeign.test());
    }

    @GetMapping(value = "/test1")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public R getJob1() {
        return R.success("测试测试", jobWantedFeign.test());
    }

    @GetMapping(value = "/findJobWantedByUserId/{userId}")
    public R getJobWantedByUserId(@PathVariable Long userId) {
        return jobWantedFeign.getJobWantedByUserId(userId);
    }

    @PostMapping(value = "/jobWanted/save")
    public R saveJobWanted(Map<String, Object> param) {
        return jobWantedFeign.saveJobWanted(param);
    }

    public R fallbackMethod() {
        return R.error("您访问的服务可能掉线了");
    }

    @GetMapping(value = "/testHystrix")
    public R testHystrix() {
        return R.success("测试Hystrix");
    }
}
