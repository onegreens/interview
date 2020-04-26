package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto implements ToObject<BookPo> {

    private Integer id;
    private String name;
    private Integer user_id;
    private Date createTime;

    @Override
    public BookPo toObject() {
        return (BookPo) new ClassUtils().inheritValue(new BookPo(), this,null);
    }
}