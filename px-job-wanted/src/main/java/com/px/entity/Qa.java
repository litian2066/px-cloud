package com.px.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Qa implements Serializable {

    @TableId
    @TableField(value = "qa_id")
    private Long id;

    @TableField(value = "qa_question")
    private String question;

    @TableField(value = "qa_answer")
    private String answer;

    @TableField(value = "qa_state")
    private int state;

}
