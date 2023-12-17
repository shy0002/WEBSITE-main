package com.ayang.website.user.service;

/**
 * @author shy
 * @date 2023/12/12
 * @description LonginService
 */
public interface LoginService {
    /**
     * 校验token是不是有效
     */
    boolean verify(String token);

    /**
     * 刷新token有效期
     */
    void renewalTokenIfNecessary(String token);

    /**
     * 登录成功，获取token
     * @return 返回token
     */
    String login(Long uid);

    /**
     * 如果token有效，返回uid
     * @return uid
     */
    Long getValidUid(String token);
}
