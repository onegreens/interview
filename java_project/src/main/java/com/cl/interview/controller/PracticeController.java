package com.cl.interview.controller;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static com.cl.interview.common.Constant.LOG_NAME;

@Slf4j
@RestController
@RequestMapping("/practice")
public class PracticeController {
    @Autowired
    PracticeService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HttpResp List(HttpServletRequest request,
                         @RequestHeader(value = "Authorization", required = false) String token,
                         @RequestParam int pageNo, @RequestParam int pageSize,
                         @RequestParam String cateId, @RequestParam Integer score
    ) {
        HttpResp resp = new HttpResp();

        try {
            resp.setData(service.getData(pageNo, pageSize, cateId, score, null));
        } catch (Exception e) {
            resp.setCode(IoTErrorCode.server_error.getErrorCode());
            resp.setMessage("习题信息列表 查询失败，数据库出错 e= " + e.getMessage());
            return resp;
        }
        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public HttpResp save(@RequestBody Map<String,Object> map, HttpServletRequest request,
                         @RequestHeader(value = "Authorization") String token) {

        request.setAttribute(LOG_NAME, "创建习题信息");
        request.setAttribute("token", token);

        return service.saveByParams((List) map.get("params"),null);
    }


}
