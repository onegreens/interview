package com.cl.interview.config.token;

import com.cl.interview.config.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RedisTokenImpl implements ITokenService {

    @Autowired
    private RedisClient redisClient;

    @Override
    public TokenModel create(Integer id) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel tokenModel = new TokenModel(id,token);
        redisClient.set(id ==null?null:String.valueOf(id),token,RedisClient.TOKEN_EXPIRES_SECOND);
        return tokenModel;
    }

    @Override
    public boolean check(TokenModel tokenModel) {
        boolean result = false;
        if (tokenModel!=null){
            String userId = tokenModel.getUserId().toString();
            String token = tokenModel.getToken();
            String authToken = redisClient.get(userId);
            if (authToken!=null&&authToken.equals(token)){
                redisClient.expire(userId,RedisClient.TOKEN_EXPIRES_SECOND);
                result = true;
            }
        }
        return result;
    }

    @Override
    public TokenModel get(String authStr) {
        TokenModel tokenModel = null;
        if (StringUtils.isNotBlank(authStr)){
            String[] modelStr = authStr.split("_");
            if (modelStr.length == 2){
                String userId = modelStr[0];
                String token = modelStr[1];
                tokenModel = new TokenModel(Integer.valueOf(userId),token);
            }
        }
        return tokenModel;
    }

    @Override
    public boolean delete(Integer id) {
        return redisClient.remove(id == null ? null : String.valueOf(id));
    }
}
