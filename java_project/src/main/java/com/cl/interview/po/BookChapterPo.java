package com.cl.interview.po;

import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.ToObject;
import com.cl.interview.entity.BookChapterEntity;
import com.cl.interview.util.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
public class BookChapterPo implements Serializable, ToObject<BookChapterEntity> {

    public BookChapterPo() {
    }

    public BookChapterPo(String id) {
        this.id = id;
    }

    private String id;
    private List<BookChapterPo> children;
    private String parentId;
    /**
     * 用户id
     */
    private String userId;

    private String bookId;
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
        if (StringUtils.isEmpty(this.id)) {
            this.setId(IdGenerator.nextId());
        }
        BookChapterEntity entity = (BookChapterEntity) ClassUtils.inheritValue(new BookChapterEntity(), this, new HashSet<String>() {
            {
                add("parent");
            }
        });

        return entity;
    }

}