package com.px.service;

import com.px.entity.JobWanted;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobWantedService {

    /*@Autowired
    private JobWantedMapper jobWantedMapper;*/

//    @Autowired
//    private JobWantedRepository jobWantedRepository;

    public List<JobWanted> findAll() {
//        return  jobWantedRepository.findAll();
        return null;
    }

    /**
     * 保存
     * @param jobWanted
     * @return
     */
    @Transactional
    public JobWanted save(JobWanted jobWanted) {
        // return this.jobWantedRepository.saveAndFlush(jobWanted);
        return null;
    }

    /**
     * 查询用户的找工作记录
     * @param userId
     * @return
     */
    public List<JobWanted> findJobWantedByUserId(Long userId) {
//        return  jobWantedRepository.findJobWantedByCreateUserId(userId);
        return null;
    }
}
