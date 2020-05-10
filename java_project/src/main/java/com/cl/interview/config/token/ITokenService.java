package com.cl.interview.config.token;

public interface ITokenService {

    /**
     * 根据id生成token
     * @param id
     * @return
     */
    TokenModel create(Integer id);

    /**
     * 检查是否存在token
     * @param tokenModel
     * @return
     */
    boolean check(TokenModel tokenModel);

    /**
     * 从前端的返回的校验字段中判断是否有token
     * @param authStr
     * @return
     */
    TokenModel get(String authStr);

    /**
     * 根据用户id删除token
     * @param id
     * @return
     */
    boolean delete(Integer id);
}
