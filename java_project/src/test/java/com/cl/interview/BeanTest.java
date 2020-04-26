package com.cl.interview;

import com.cl.interview.common.ToObject;
import com.cl.interview.dto.BookDto;
import org.junit.jupiter.api.Test;


public class BeanTest {

    @Test
    void beanTestCopy() {
        System.out.println("test");
        BookDto dto = new BookDto();
        dto.setId(1);
        dto.setName("测试");
        dto.setUser_id(1);
        System.out.println(dto);

    }


}
