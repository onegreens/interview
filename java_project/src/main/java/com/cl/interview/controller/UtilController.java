package com.cl.interview.controller;

import com.cl.interview.common.HttpResp;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.service.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController

@RequestMapping("/util")
public class UtilController {

    @Autowired
    UtilService service;
    @RequestMapping(value = "/doJson", method = RequestMethod.GET)
    public HttpResp doJson(@RequestHeader(value = "Authorization") String token,
                           HttpServletRequest request, @RequestParam String alias) throws ArgumentException {
        HttpResp resp = new HttpResp();
        service.doJson("bookChapter");
        service.doJson("bookContent");
        service.doJson("question");
//        service.doJson(alias);
        return resp;
    }
}
