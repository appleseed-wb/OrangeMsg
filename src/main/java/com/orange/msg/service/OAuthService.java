package com.orange.msg.service;

/**
 * 鉴权
 */
public interface OAuthService {

    /**
     * 验证access token是否有效
     * @param accessToken
     * @return
     */
    public boolean checkAccessToken(String accessToken);
}
