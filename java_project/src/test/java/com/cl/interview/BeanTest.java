package com.cl.interview;

import com.cl.interview.dto.BookDto;
import com.cl.interview.po.BookPo;
import com.cl.interview.util.ClassUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class BeanTest {

    @Test
    void beanTestCopy() {
        System.out.println("test");
        BookDto dto = new BookDto();
        dto.setId(1L);
        dto.setLevel(2);
        dto.setName("测试");
        dto.setSort(3);
        dto.setPage(4);
        dto.setParentId(5L);
        dto.setUserId(6L);

//        BookPo other = (BookPo) new ClassUtils().inheritValue(new BookPo(), dto);
//        System.out.println(other);


    }


}
