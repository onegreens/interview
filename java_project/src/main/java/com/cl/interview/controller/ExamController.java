package com.cl.interview.controller;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.po.QuestionPo;
import com.cl.interview.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    QuestionService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HttpResp List(HttpServletRequest request,
                         @RequestHeader(value = "Authorization", required = false) String token, @RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String cateId
    ) {
        HttpResp resp = new HttpResp();
        QuestionPo po = new QuestionPo();
        po.setCateId(cateId);
        try {
            resp.setData(service.getDataByPage(pageNo, pageSize, po, new ArrayList<String>(), null));
        } catch (Exception e) {
            resp.setCode(IoTErrorCode.server_error.getErrorCode());
            resp.setMessage("习题信息列表 查询失败，数据库出错 e= " + e.getMessage());
            return resp;
        }
        return resp;
    }

}
