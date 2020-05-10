package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.entity.UserEntity;


public interface UserService{
    HttpResp login(String username,String password);
}
