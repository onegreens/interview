package com.cl.interview.entity;

import com.cl.interview.common.ToData;
import com.cl.interview.po.QuestionPo;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_question")
@Data
@AllArgsConstructor
public class QuestionEntity implements ToData<QuestionPo> {
    public QuestionEntity() {
    }

    public QuestionEntity(QuestionPo po) {
        this.id = po.getId();
        this.cateId = po.getCateId();
        this.title = po.getTitle();
        this.answer = po.getAnswer();
        this.href = po.getHref();
        this.createTime = po.getCreateTime();
    }

    @Id
    @GeneratedValue
    private Long id;
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
    public QuestionPo toData() {
        return new QuestionPo(this.id, this.cateId, this.title, this.answer, this.href, this.createTime);
    }
}