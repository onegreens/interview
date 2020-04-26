package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.Page;
import com.cl.interview.dto.QuestionDto;
import com.cl.interview.po.QuestionPo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService extends CommonService<QuestionPo> {


    /**
     * 分页查询 含排序
     *
     * @param pageNo
     * @param pageSize
     * @param obj
     * @param orderBysList
     * @return
     */
    public Page getDataByPage(int pageNo, int pageSize, QuestionPo obj, List<String> orderBysList, String search);


    HttpResp create(QuestionDto dto);

    HttpResp update(QuestionDto dto);

    String doSerializable();

    void saveByFile(MultipartFile mf);
}
