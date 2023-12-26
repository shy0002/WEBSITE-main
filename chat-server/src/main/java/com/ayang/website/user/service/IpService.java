package com.ayang.website.user.service;

/**
 * @author shy
 * @date 2023/12/25
 * @description IpService
 */
public interface IpService {

    /**
     * 用户ip详情的解析
     * @param uid 用户id
     */
    void refreshIpDetailAsync(Long uid);
}
