package com.cl.interview;

import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.ToObject;
import com.cl.interview.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BeanTest {
    @Test
    void beanTestCopy() {
        System.out.println(new StringBuilder().append("Byte-->>").append("字节数：").append(Byte.BYTES).append(";位数：").
                append(Byte.SIZE).append("; 最小值-->最大值:").append(Byte.MIN_VALUE).append("-->").append(Byte.MAX_VALUE));
        System.out.println(new StringBuilder().append("Short-->>").append("字节数：").append(Short.BYTES).append(";位数：").
                append(Short.SIZE).append("; 最小值-->最大值:").append(Short.MIN_VALUE).append("-->").append(Short.MAX_VALUE));
        System.out.println(new StringBuilder().append("Integer-->>").append("字节数：").append(Integer.BYTES).append(";位数：").
                append(Integer.SIZE).append("; 最小值-->最大值:").append(Integer.MIN_VALUE).append("-->").append(Integer.MAX_VALUE));
        System.out.println(new StringBuilder().append("Long-->>").append("字节数：").append(Long.BYTES).append(";位数：").
                append(Long.SIZE).append("; 最小值-->最大值:").append(Long.MIN_VALUE).append("-->").append(Long.MAX_VALUE));
        System.out.println(new StringBuilder().append("Float-->>").append("字节数：").append(Float.BYTES).append(";位数：").
                append(Float.SIZE).append("; 最小值-->最大值:").append(Float.MIN_VALUE).append("-->").append(Float.MAX_VALUE));
        System.out.println(new StringBuilder().append("Double-->>").append("字节数：").append(Double.BYTES).append(";位数：").
                append(Double.SIZE).append("; 最小值-->最大值:").append(Double.MIN_VALUE).append("-->").append(Double.MAX_VALUE));
        System.out.println(new StringBuilder().append("Character-->>").append("字节数：").append(Character.BYTES).append(";位数：").
                append(Character.SIZE).append("; 最小值-->最大值:").append((int) Character.MIN_VALUE).append("-->").append((int) Character.MAX_VALUE));
    }


}

class DBLock {
    private volatile static DBLock instace;
    private DBLock() {}
    public static DBLock getInstace() {
        if (instace == null) {
            synchronized (DBLock.class) {
                if (instace == null) {
                    instace = new DBLock();
                }
            }
        }
        return instace;
    }
}