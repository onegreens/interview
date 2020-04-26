package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookContentPo;
import com.cl.interview.po.BookPo;
import com.cl.interview.po.QuestionPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto implements ToObject<QuestionPo> {


    private Integer id;
    private String cateId;
    private String title;
    private String answer;
    private String href;
    private Date createTime;

    @Override
    public QuestionPo toObject() {
        return (QuestionPo) new ClassUtils().inheritValue(new QuestionPo(), this,null);
    }
}