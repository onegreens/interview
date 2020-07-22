package com.cl.interview.service;

import com.cl.interview.common.HttpResp;
import com.cl.interview.exception.ArgumentException;

public interface UtilService {

    HttpResp doJson(String tableAlias) throws ArgumentException;
}
