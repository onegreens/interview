package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.Page;
import com.cl.interview.dto.BookDto;
import com.cl.interview.po.BookPo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BookService extends CommonService<BookPo> {


    /**
     * 分页查询 含排序
     *
     * @param pageNo
     * @param pageSize
     * @param obj
     * @param orderBysList
     * @return
     */
    public Page getDataByPage(int pageNo, int pageSize, BookPo obj, List<String> orderBysList, String search);


    HttpResp create(BookDto dto);

    HttpResp update(BookDto dto);

    String doSerializable();

    void saveByFile(MultipartFile mf);

    List<Map> treeData();
}
