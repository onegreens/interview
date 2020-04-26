package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookChapterPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

@Data
public class BookChapterDto implements ToObject<BookChapterPo> {
    private Integer id;
    private Integer parentId;
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

    @Override
    public BookChapterPo toObject() {
        BookChapterPo po =  (BookChapterPo) new ClassUtils().inheritValue(new BookChapterPo(), this,null);
        po.setParent(new BookChapterPo(this.parentId));
        return po;
    }
}