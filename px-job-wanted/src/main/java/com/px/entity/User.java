package com.px.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "user")
public class User {

    @TableId
    private Long userId;

    private String name;

    private int age;

    private String email;

    private Long managerId;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private String remark;

}
