package com.cl.interview.po;

import com.cl.interview.common.ToObject;
import com.cl.interview.dto.BookDto;
import com.cl.interview.entity.BookEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
public class BookPo implements Serializable, ToObject<BookEntity> {

    public BookPo() {
    }

    public BookPo(Integer id) {
        this.id = id;
    }

    private Integer id;
    private List<BookPo> children;
    private BookPo parent;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 名称
     */
    private String name;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 页面
     */
    private Integer page;
    /**
     * 创建时间
     */
    private Date createTime;


    @Override
    public BookEntity toObject() {
        BookEntity entity = (BookEntity) new ClassUtils().inheritValue(new BookEntity(), this, new HashSet<String>() {
            {
                add("parent");
            }
        });
        if (this.parent != null) entity.setParent(this.parent.toObject());
        return entity;
    }

}