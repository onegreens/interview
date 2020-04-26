package com.cl.interview.entity;

import com.cl.interview.common.ToObject;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * 书籍章节管理
 *
 * @author wengzhonghui
 */
@Entity
@Data
@Table(name = "t_book")
public class BookEntity implements ToObject<BookPo> {
    public BookEntity() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 父级id
     */
    @JoinColumn(name = "PARENT_ID", insertable = false, nullable = true, updatable = false)
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private BookEntity parent;


    @JoinColumn(name = "PARENT_ID", insertable = false, nullable = true, updatable = false)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BookEntity> children;
    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private Integer userId;
    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;
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
     * 页面
     */
    @Column(name = "PAGE")
    private Integer page;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Override
    public BookPo toObject() {
        BookPo po = (BookPo) new ClassUtils().inheritValue(new BookPo(), this, new HashSet<String>() {
            {
                //避免反射赋值时无法执行
                add("parent");
                add("children");
            }
        });
        if (this.children != null&&this.children.size() > 0) {
            List<BookPo> list = new ArrayList<>();
            for (int i = 0, size = this.children.size(); i < size; i++) {
                list.add(this.children.get(i).toObject());
            }
            po.setChildren(list);
        }
        return po;
    }
}