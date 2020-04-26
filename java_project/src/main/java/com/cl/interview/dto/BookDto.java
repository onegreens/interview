package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

@Data
public class BookDto implements ToObject<BookPo> {
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
    public BookPo toObject() {
        BookPo po =  (BookPo) new ClassUtils().inheritValue(new BookPo(), this,null);
        po.setParent(new BookPo(this.parentId));
        return po;
    }
}