package com.cl.interview.controller;

import com.cl.interview.common.HttpResp;
import com.cl.interview.entity.UserEntity;
import com.cl.interview.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HttpResp login(String username, String password, HttpSession session){
        HttpResp<UserEntity> response = userService.login(username, password);
        if (response.isSuccess()){
            session.setAttribute("CURRENT_USER",response.getData());
        }
        return response;
    }
}
