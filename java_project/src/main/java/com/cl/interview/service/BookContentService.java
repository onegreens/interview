package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.MDData;
import com.cl.interview.common.Page;
import com.cl.interview.dto.BookContentDto;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.po.BookContentPo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookContentService extends CommonService<BookContentPo> {


    /**
     * 分页查询 含排序
     *
     * @param pageNo
     * @param pageSize
     * @param obj
     * @param orderBysList
     * @return
     */
    public Page getDataByPage(int pageNo, int pageSize, BookContentPo obj, List<String> orderBysList, String search);


    HttpResp create(BookContentDto dto) throws ArgumentException;

    HttpResp update(BookContentDto dto) throws ArgumentException;

    String doSerializable();

    void saveByFile(MultipartFile mf);

    List<MDData> toMD(String chapterId);
}
