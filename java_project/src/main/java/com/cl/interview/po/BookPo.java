package com.cl.interview.po;

import com.cl.interview.common.ToObject;
import com.cl.interview.dto.BookDto;
import com.cl.interview.entity.BookEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class BookPo implements Serializable ,ToObject<BookEntity> {

    public BookPo() {
    }

    private Integer id;
    private String name;
    private Integer user_id;
    private Date createTime;

    @Override
    public BookEntity toObject() {
        return (BookEntity) new ClassUtils().inheritValue(new BookEntity(), this,null);

    }
}