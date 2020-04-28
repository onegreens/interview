package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.MDData;
import com.cl.interview.common.Page;
import com.cl.interview.dto.BookChapterDto;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.po.BookChapterPo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BookChapterService extends CommonService<BookChapterPo> {


    /**
     * 分页查询 含排序
     *
     * @param pageNo
     * @param pageSize
     * @param obj
     * @param orderBysList
     * @return
     */
    Page getDataByPage(int pageNo, int pageSize, BookChapterPo obj, List<String> orderBysList, String search);


    HttpResp create(BookChapterDto dto) throws ArgumentException;

    HttpResp update(BookChapterDto dto) throws ArgumentException;

    String doSerializable();

    void saveByFile(MultipartFile mf);

    void refreshCache();

    List<Map> treeData(String bookId);

    List<MDData> toMD(String bookId);
}
