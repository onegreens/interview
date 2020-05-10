package com.cl.interview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {
    public static final long TOKEN_EXPIRES_SECOND = 1800;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 向redis中设值
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向redis中设置，同时设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key,String value,long time){
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value);
            expire(key,time);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    public String get(String key){
        String result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean expire(String key, long time) {
        boolean result = false;
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据key删除对应value
     * @param key
     * @return
     */
    public boolean remove(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
