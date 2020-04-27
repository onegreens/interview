package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.QuestionPo;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_question")
@Data
@AllArgsConstructor
public class QuestionEntity implements ToObject<QuestionPo> {
    public QuestionEntity() {
    }

    @Id

    @Column(name="ID")
    private String id;
    @Column(name = "cate_id")
    private String cateId;
    @Column(name = "title")
    private String title;
    @Column(name = "answer")
    private String answer;
    @Column(name = "href")
    private String href;
    @Column(name = "create_time")
    private Date createTime;


    @Override
    public QuestionPo toObject() {
        return (QuestionPo) new ClassUtils().inheritValue(new QuestionPo(), this,null);

    }
}