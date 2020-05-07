package com.cl.interview.po;

import com.cl.interview.common.ToObject;
import com.cl.interview.entity.UserEntity;

import lombok.Data;

import java.io.Serializable;

@Data

public class UserPo implements Serializable, ToObject<UserEntity> {

    public UserPo(){

    }
    @Override
    public UserEntity toObject() {
        return null;
    }
}
