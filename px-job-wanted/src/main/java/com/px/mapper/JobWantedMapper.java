package com.px.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.entity.JobWanted;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface JobWantedMapper extends BaseMapper<JobWanted> {

    public List<JobWanted> findAll();
}
