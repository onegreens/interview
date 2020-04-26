package com.cl.interview.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    @Value("${idGenerator.workId}")
    private long workId;

    @Value("${idGenerator.dataId}")
    private long dataId;

    private static SnowflakeIdWorker idWorker;

    public IdGenerator(){
        this.idWorker = new SnowflakeIdWorker(workId,dataId);
    }

    public static Long nextId(){
        return idWorker.nextId();
    }

}
