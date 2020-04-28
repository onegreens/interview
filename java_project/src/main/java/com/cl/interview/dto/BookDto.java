package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto implements ToObject<BookPo> {

    private String id;
    private String name;
    private String type;
    private String userId;
    private Date createTime;
    private Integer noteNum;

    @Override
    public BookPo toObject() {
        return (BookPo) ClassUtils.inheritValue(new BookPo(), this,null);
    }
}