package com.cl.interview.dao;

import com.cl.interview.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,Integer> {

    int findByUsername(String username);

    UserEntity selectLogin(String username,String password);
}
