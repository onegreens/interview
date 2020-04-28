package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_book")
@Data
@AllArgsConstructor
public class BookEntity implements ToObject<BookPo> {
    public BookEntity() {
    }

    @Id

    @Column(name="ID")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "note_num")
    private Integer noteNum = 0;
    @Column(name = "create_time")
    private Date createTime;


    @Override
    public BookPo toObject() {
        return (BookPo) ClassUtils.inheritValue(new BookPo(), this,null);

    }
}