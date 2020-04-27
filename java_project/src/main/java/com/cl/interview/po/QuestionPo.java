package com.cl.interview.po;

import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.ToObject;
import com.cl.interview.dto.QuestionDto;
import com.cl.interview.entity.QuestionEntity;
import com.cl.interview.util.ClassUtils;
import com.cl.interview.util.SerializableFile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class QuestionPo implements Serializable ,ToObject<QuestionEntity> {

    public QuestionPo() {
    }

    private String id;
    private String cateId;
    private String title;
    private String answer;
    private String href;
    private Date createTime;


    public QuestionPo(QuestionDto dto) {
        super();
        if (dto != null) {
            this.id = dto.getId();
            this.cateId = dto.getCateId();
            this.title = dto.getTitle();
            this.answer = dto.getAnswer();
            this.href = dto.getHref();
            this.createTime = dto.getCreateTime();
        }
    }

    @Override
    public QuestionEntity toObject() { if (this.id == null) {
        this.setId(IdGenerator.nextId());
    }
        return (QuestionEntity) new ClassUtils().inheritValue(new QuestionEntity(), this,null);

    }
}