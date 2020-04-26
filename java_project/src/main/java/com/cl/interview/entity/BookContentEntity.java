package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookContentPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;


/**
 * 书籍章节管理
 *
 * @author wengzhonghui
 */
@Entity
@Data
@Table(name = "t_book_content")
public class BookContentEntity implements ToObject<BookContentPo> {
    public BookContentEntity() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn(name = "BOOK_ID", insertable = false, nullable = true, updatable = false)
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private BookEntity bookEntity;
    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private Integer userId;
    /**
     * 排序
     */
    @Column(name = "DEGREE")
    private Integer degree;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 层级
     */
    @Column(name = "LEVEL")
    private Integer level;
    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;
    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;
    /**
     * 见解
     */
    @Column(name = "THINK")
    private String think;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Override
    public BookContentPo toObject() {
        BookContentPo po = (BookContentPo) new ClassUtils().inheritValue(new BookContentPo(), this,null);
        if (this.bookEntity != null)    po.setBookPo(this.getBookEntity().toObject());
        return po;
    }
}