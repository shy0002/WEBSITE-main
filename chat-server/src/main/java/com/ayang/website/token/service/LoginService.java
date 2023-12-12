package com.ayang.website.token.service;

/**
 * @author shy
 * @date 2023/12/12
 * @description LonginService
 */
public interface LoginService {
    /**
     * 用户登录页获取token
     */
    String login(Long uid);
}
