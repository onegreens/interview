package com.cl.interview.controller;

import com.cl.interview.common.HttpResp;
import com.cl.interview.exception.ArgumentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static com.cl.interview.common.IoTErrorCode.*;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler({ArgumentException.class})
    public HttpResp ArgumentException(Exception e) {
        return HttpResp.fail(bad_request_param.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({IOException.class})
    public HttpResp IOException(Exception e) {
        return HttpResp.fail(server_error.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public HttpResp exception(Exception e) {
        return HttpResp.fail(server_error.getErrorCode(), e.getMessage());
    }
}
