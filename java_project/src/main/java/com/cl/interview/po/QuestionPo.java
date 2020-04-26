package com.cl.interview.po;

import com.cl.interview.dto.QuestionDto;
import com.cl.interview.util.SerializableFile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class QuestionPo implements Serializable {

    public QuestionPo() {
    }

    private Long id;
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
}