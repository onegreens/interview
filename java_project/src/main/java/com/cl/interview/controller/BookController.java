package com.cl.interview.controller;

import com.cl.interview.common.Constant;
import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.dto.BookDto;
import com.cl.interview.po.BookPo;
import com.cl.interview.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

import static com.cl.interview.common.Constant.LOG_NAME;

@Slf4j
@RestController

@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HttpResp List(HttpServletRequest request,
                         @RequestHeader(value = "Authorization", required = false) String token, @RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String search) {
        HttpResp resp = new HttpResp();
        BookPo po = new BookPo();
        try {
            resp.setData(bookService.getDataByPage(pageNo, pageSize, po, new ArrayList<String>(), search));
        } catch (Exception e) {
            resp.setCode(IoTErrorCode.server_error.getErrorCode());
            resp.setMessage("习题信息列表 查询失败，数据库出错 e= " + e.getMessage());
            return resp;
        }
        return resp;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)

    public HttpResp create(@RequestBody BookDto dto, HttpServletRequest request,
                           @RequestHeader(value = "Authorization") String token) {

        request.setAttribute(LOG_NAME, "创建习题信息");
        request.setAttribute("token", token);

        return bookService.create(dto);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)

    public HttpResp deleteQuestionPo(@PathVariable(value = "id") String id,
                                     @RequestHeader(value = "Authorization") String token, HttpServletRequest request) {
        HttpResp resp = new HttpResp();
        BookPo po = bookService.getOne(id);
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("删除习题信息失败，习题信息不存在");
            return resp;
        }

        request.setAttribute(LOG_NAME, "删除习题信息 " + po.getId());
        request.setAttribute("token", token);
        bookService.delete(id);

        return resp;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public HttpResp updateQuestionPo(@RequestBody BookDto dto, @RequestHeader(value = "Authorization") String token,
                                     HttpServletRequest request) {

        request.setAttribute(LOG_NAME, "编辑习题信息  " + dto.getId());
        request.setAttribute("token", token);
        return bookService.update(dto);
    }

    @RequestMapping(value = "/doSerializable", method = RequestMethod.POST)
    public HttpResp doSerializable(@RequestHeader(value = "Authorization") String token) {
        HttpResp resp = new HttpResp();
        bookService.doSerializable();
        return resp;
    }

    @RequestMapping(value = "/toMDFile", method = RequestMethod.GET)
    public HttpResp toMDFile(@RequestHeader(value = "Authorization") String token,
                                   HttpServletRequest request, @RequestParam String bookId) {
        HttpResp resp = new HttpResp();
        bookService.toMDFile(bookId);
        return resp;
    }


    /**
     * 读取书籍文件
     *
     * @param request
     * @param token
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public HttpResp importExcel(HttpServletRequest request,
                                @RequestHeader(value = "Authorization", required = false) String token) {
        HttpResp response = new HttpResp();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if (multipartResolver.isMultipart(request)) {

                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multipartRequest.getFileNames();
                while (iterator.hasNext()) {
                    MultipartFile mf = multipartRequest.getFile(iterator.next());
                    bookService.saveByFile(mf);
                }
            }
        } catch (Exception e) {
            log.error("importExcel fail e {}", e.getMessage());
            response.setCode(IoTErrorCode.server_error.getErrorCode());
            return response;
        }


        request.setAttribute(Constant.LOG_NAME, "导入成功");
        return response;
    }
}
