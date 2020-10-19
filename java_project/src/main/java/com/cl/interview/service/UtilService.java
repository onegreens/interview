package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.exception.ArgumentException;
import org.springframework.web.multipart.MultipartFile;

public interface UtilService {

    HttpResp doJson(String tableAlias) throws ArgumentException;

    String saveImage(MultipartFile mf);

}
