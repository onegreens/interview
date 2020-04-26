package com.cl.interview.dto;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookContentPo;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;

@Data
public class BookContentDto implements ToObject<BookContentPo> {

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 书籍Id
     */
    private Integer bookId;
    /**
     * 标题
     */
    private String title;
    private String content;
    private String think;
    private Integer degree;

    @Override
    public BookContentPo toObject() {
        BookContentPo po = (BookContentPo) new ClassUtils().inheritValue(new BookContentPo(), this, null);
        po.setBookPo(new BookPo(this.bookId));
        return po;
    }
}