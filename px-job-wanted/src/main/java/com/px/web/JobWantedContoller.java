package com.px.web;

import com.px.entity.JobWanted;
import com.px.service.JobWantedService;
import com.px.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JobWantedContoller {

    @Autowired
    private JobWantedService jobWantedService;

    @GetMapping(value = "/list")
    public R getJobWanted() {
        Map<String, Object> map = new HashMap<>();
        map.put("jobWantedList", jobWantedService.findAll());
        return R.success("success", map);
    }

    @GetMapping(value = "/findJobWantedByUserId/{userId}")
    public R getJobWantedByUserId(@PathVariable Long userId) {
        return R.success("success", jobWantedService.findJobWantedByUserId(userId));
    }

//    @PostMapping(value = "/save")
//    public R saveJobWanted(JobWanted jobWanted) {
//        JobWanted JobWantedSaved = jobWantedService.save(jobWanted);
//        return JobWantedSaved.getJobWantedId() != null ?
//                R.success("保存成功", JobWantedSaved) :
//                R.error("保存失败");
//    }

    @PostMapping(value = "/save")
    public R saveJobWanted(@RequestBody Map<String, Object> param) {
        JobWanted jobWanted = (JobWanted) param.get("jobWanted");
        JobWanted JobWantedSaved = jobWantedService.save(jobWanted);
        return JobWantedSaved.getJobWantedId() != null ?
                R.success("保存成功", JobWantedSaved) :
                R.error("保存失败");
    }

    @GetMapping(value = "/test")
    public R test() {
        return R.success("test");
    }


}
