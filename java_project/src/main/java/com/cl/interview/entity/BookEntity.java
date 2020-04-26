package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_question")
@Data
@AllArgsConstructor
public class BookEntity implements ToObject<BookPo> {
    public BookEntity() {
    }

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "create_time")
    private Date createTime;


    @Override
    public BookPo toObject() {
        return (BookPo) new ClassUtils().inheritValue(new BookPo(), this,null);

    }
}