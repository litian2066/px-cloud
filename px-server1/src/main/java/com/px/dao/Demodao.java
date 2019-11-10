package com.px.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Demodao {

    @Insert("insert into t_test(name) values(#{name})")
    public void insert(@Param("name") String name);

}
