package com.cl.interview.po;

import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.ToObject;
import com.cl.interview.entity.BookContentEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class BookContentPo implements Serializable, ToObject<BookContentEntity> {
    public BookContentPo() {
    }


    private String id;
    /**
     * 用户id
     */
    private String userId;

    private String bookId;
    private String chapterId;

    /**
     * 标题
     */
    private String title;
    private String content;
    private String think;
    private Integer degree;
    private Date createTime;

    @Override
    public BookContentEntity toObject() {
        if (this.id == null) {
            this.setId(IdGenerator.nextId());
        }
        BookContentEntity entity = (BookContentEntity) ClassUtils.inheritValue(new BookContentEntity(), this, null);
        return entity;
    }
}