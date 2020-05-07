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

    private HttpResp(int code,T data){
        this.code = code;
        this.data = data;
    }
    private HttpResp(int code){
        this.code = code;
    }
    private HttpResp(int code,String message){
        this.code = code;
        this.message = message;
    }
    public boolean isSuccess(){
        return this.code == IoTErrorCode.SUCCESS.getErrorCode();
    }
    public static <T> HttpResp<T> createBySuccess(String message,T data){
        return new HttpResp<T>(IoTErrorCode.SUCCESS.getErrorCode(),message,data);
    }
    public static <T> HttpResp<T> createBySuccess(){
        return new HttpResp<T>(IoTErrorCode.SUCCESS.getErrorCode());
    }

    public static <T> HttpResp<T> createBySuccessMessage(String message){
        return new HttpResp<T>(IoTErrorCode.SUCCESS.getErrorCode(),message);
    }
    public static HttpResp fail(String message){
        return new HttpResp(IoTErrorCode.server_error.getErrorCode(),message,null);
    }

    public static HttpResp fail(Integer code,String message){
        return new HttpResp(code,message,null);
    }
}
