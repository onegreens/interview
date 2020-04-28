package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookChapterPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

import java.util.Date;

@Data
public class BookChapterDto implements ToObject<BookChapterPo> {
    private String id;
    private String parentId;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户id
     */
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
    public BookChapterPo toObject() {
        BookChapterPo po =  (BookChapterPo) ClassUtils.inheritValue(new BookChapterPo(), this,null);
        return po;
    }
}