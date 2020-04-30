package com.cl.interview.po;

import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.ToObject;
import com.cl.interview.entity.PracticeEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class PracticePo implements Serializable, ToObject<PracticeEntity> {

    public PracticePo() {
    }

    public PracticePo(String id, String questionId, String userId, Integer score) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.score = score;
    }

    private String id;
    private String questionId;
    private String userId;
    private Integer score = 0;
    private String scoreRecord;
    private Date updateTime;

    @Override
    public PracticeEntity toObject() {
        if (StringUtils.isEmpty(this.id)) {
            this.setId(IdGenerator.nextId());
        }
        return (PracticeEntity) ClassUtils.inheritValue(new PracticeEntity(), this, null);

    }
}