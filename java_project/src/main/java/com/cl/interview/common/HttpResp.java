package com.cl.interview.common;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResp<T> {

    private Integer code;
    private String message;
    private T data;

    public HttpResp() {
        code = IoTErrorCode.SUCCESS.getErrorCode();
    }


    public static HttpResp fail(String message){
        return new HttpResp(IoTErrorCode.server_error.getErrorCode(),message,null);
    }

    public static HttpResp fail(Integer code,String message){
        return new HttpResp(code,message,null);
    }
}
