package com.cl.interview.controller;

import com.cl.interview.common.Constant;
import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.service.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /**
     * 读取书籍文件
     *
     * @param request
     * @param token
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public HttpResp importDeviceExcel(HttpServletRequest request,
                                      @RequestHeader(value = "Authorization", required = false) String token) {

        HttpResp response = new HttpResp();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List urls =new ArrayList<String>();
        try {
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multipartRequest.getFileNames();
                while (iterator.hasNext()) {
                    MultipartFile mf = multipartRequest.getFile(iterator.next());
                    urls.add(service.saveImage(mf));
                }
            }
        } catch (Exception e) {
            log.error("importDeviceExcel fail e {}", e.getMessage());
            response.setCode(IoTErrorCode.server_error.getErrorCode());
            return response;
        }
        response.setData(urls);

        request.setAttribute(Constant.LOG_NAME, "导入成功");
        return response;
    }
}
