package com.px.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class JobWanted implements Serializable {


    @TableId
    private Long jobWantedId;


    private Long companyId;

    private String companyName;


    private Long jobId;


    private String summary;


    private Long qaId;


    private Date createDate;


    private Long createUserId;


    private int state;



}
