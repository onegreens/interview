package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.po.PracticePo;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_practice")
@Data
@AllArgsConstructor
public class PracticeEntity implements ToObject<PracticePo> {
    public PracticeEntity() {
    }

    @Id
    @Column(name="ID")
    private String id;

    @Column(name = "question_id")
    private String questionId;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "score")
    private Integer score = 0;
    @Column(name = "score_record")
    private String scoreRecord;
    @Column(name = "update_time")
    private Date updateTime;


    @Override
    public PracticePo toObject() {
        return (PracticePo) ClassUtils.inheritValue(new PracticePo(), this,null);

    }
}