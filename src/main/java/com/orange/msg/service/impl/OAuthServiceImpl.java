package com.orange.msg.service.impl;

import com.orange.msg.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean checkAccessToken(String accessToken) {
        return !getAccessTokenInfoByAccessToken(accessToken).isEmpty();
    }

    public Map getAccessTokenInfoByAccessToken(String accessToken) {
        Map map = stringRedisTemplate.opsForHash().entries(accessToken);
        return map;
    }
}
