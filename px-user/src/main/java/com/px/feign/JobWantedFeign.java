package com.px.feign;

//import com.px.entity.JobWanted;
import com.px.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "server-job-wanted")
public interface JobWantedFeign {

    @GetMapping(value = "/list")
    public R getJobWanted();

    @GetMapping(value = "/findJobWantedByUserId/{userId}")
    public R getJobWantedByUserId(@PathVariable Long userId);

    @PostMapping(value = "/save")
    public R saveJobWanted(@RequestBody Map<String, Object> param);

    @GetMapping(value = "/test")
    public R test();
}
