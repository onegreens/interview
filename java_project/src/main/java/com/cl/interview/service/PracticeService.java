package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.Page;
import com.cl.interview.po.PracticePo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PracticeService extends CommonService<PracticePo> {


    Page getData(int pageNo, int pageSize, String cateId, Integer score,String userId);

    /**
     * 分页查询 含排序
     *
     * @param pageNo
     * @param pageSize
     * @param obj
     * @param orderBysList
     * @return
     */
    Page getDataByPage(int pageNo, int pageSize, PracticePo obj, List<String> orderBysList,String search);

    String doSerializable();

    HttpResp saveByParams(List params,String userId);

    void createOrUpdate(PracticePo po);

    void saveByFile(MultipartFile mf);
}
