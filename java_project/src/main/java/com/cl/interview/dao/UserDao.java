package com.cl.interview.dao;

import com.cl.interview.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<UserEntity,Integer> {
    @Query(value = "select count(1) from t_user where username = :username",nativeQuery=true)
    int findByUsername(@Param(value = "username")String username);

    UserEntity findByUsernameAndPassword(String username,String password);
}
