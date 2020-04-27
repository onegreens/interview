package com.cl.interview.controller;

import com.cl.interview.common.Constant;
import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.dto.BookChapterDto;
import com.cl.interview.po.BookChapterPo;
import com.cl.interview.service.BookChapterService;
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

@RequestMapping("/bookChapter")
public class BookChapterController {
    @Autowired
    BookChapterService bookChapterService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HttpResp List(HttpServletRequest request,
                         @RequestHeader(value = "Authorization", required = false) String token, @RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String search, @RequestParam String bookId) {
        HttpResp resp = new HttpResp();
        BookChapterPo po = new BookChapterPo();
        po.setBookId(bookId);
        try {
            resp.setData(bookChapterService.getDataByPage(pageNo, pageSize, po, new ArrayList<String>(), search));
        } catch (Exception e) {
            resp.setCode(IoTErrorCode.server_error.getErrorCode());
            resp.setMessage("书籍信息列表 查询失败，数据库出错 e= " + e.getMessage());
            return resp;
        }
        return resp;
    }

    @RequestMapping(value = "/treeData", method = RequestMethod.GET)
    public HttpResp List(HttpServletRequest request,
                         @RequestHeader(value = "Authorization", required = false) String token, @RequestParam String bookId) {
        HttpResp resp = new HttpResp();
        try {
            resp.setData(bookChapterService.treeData(bookId));
        } catch (Exception e) {
            resp.setCode(IoTErrorCode.server_error.getErrorCode());
            resp.setMessage("书籍信息列表 查询失败，数据库出错 e= " + e.getMessage());
            return resp;
        }
        return resp;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)

    public HttpResp create(@RequestBody BookChapterDto dto, HttpServletRequest request,
                           @RequestHeader(value = "Authorization") String token) {
        request.setAttribute(LOG_NAME, "创建书籍信息");
        request.setAttribute("token", token);

        return bookChapterService.create(dto);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)

    public HttpResp deleteBookPo(@PathVariable(value = "id") String id,
                                 @RequestHeader(value = "Authorization") String token, HttpServletRequest request) {
        HttpResp resp = new HttpResp();
        BookChapterPo po = bookChapterService.getOne(id);
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("删除书籍信息失败，书籍信息不存在");
            return resp;
        }

        request.setAttribute(LOG_NAME, "删除书籍信息 " + po.getId());
        request.setAttribute("token", token);
        bookChapterService.delete(id);

        return resp;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public HttpResp updateBookPo(@RequestBody BookChapterDto dto, @RequestHeader(value = "Authorization") String token,
                                 HttpServletRequest request) {

        request.setAttribute(LOG_NAME, "编辑书籍信息  " + dto.getId());
        request.setAttribute("token", token);
        return bookChapterService.update(dto);
    }

    @RequestMapping(value = "/doSerializable", method = RequestMethod.POST)
    public HttpResp doSerializable(@RequestHeader(value = "Authorization") String token,
                                   HttpServletRequest request) {
        HttpResp resp = new HttpResp();
        bookChapterService.doSerializable();
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
    public HttpResp importDeviceExcel(HttpServletRequest request,
                                      @RequestHeader(value = "Authorization", required = false) String token) {

        HttpResp response = new HttpResp();


        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if (multipartResolver.isMultipart(request)) {

                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multipartRequest.getFileNames();
                while (iterator.hasNext()) {
                    MultipartFile mf = multipartRequest.getFile(iterator.next());
                    bookChapterService.saveByFile(mf);
                }
            }
        } catch (Exception e) {
            log.error("importDeviceExcel fail e {}", e.getMessage());
            response.setCode(IoTErrorCode.server_error.getErrorCode());
            return response;
        }


        request.setAttribute(Constant.LOG_NAME, "导入成功");
        return response;
    }

    /**
     * 刷新缓存
     *
     * @param request
     * @param token
     * @return
     */
    @RequestMapping(value = "/refreshCache", method = RequestMethod.POST)
    public HttpResp refreshCache(HttpServletRequest request,
                                      @RequestHeader(value = "Authorization", required = false) String token) {

        HttpResp response = new HttpResp();


        bookChapterService.refreshCache();
        return response;
    }
}
