package com.cl.interview.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {

    private Long id;
    private String cateId;
    private String title;
    private String answer;
    private String href;
    private Date createTime;

}