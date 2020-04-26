package com.cl.interview.po;

import com.cl.interview.common.ToObject;
import com.cl.interview.entity.BookChapterEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
public class BookChapterPo implements Serializable, ToObject<BookChapterEntity> {

    public BookChapterPo() {
    }

    public BookChapterPo(Integer id) {
        this.id = id;
    }

    private Integer id;
    private List<BookChapterPo> children;
    private BookChapterPo parent;
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
    public BookChapterEntity toObject() {
        BookChapterEntity entity = (BookChapterEntity) new ClassUtils().inheritValue(new BookChapterEntity(), this, new HashSet<String>() {
            {
                add("parent");
            }
        });
        if (this.parent != null) entity.setParent(this.parent.toObject());
        return entity;
    }

}