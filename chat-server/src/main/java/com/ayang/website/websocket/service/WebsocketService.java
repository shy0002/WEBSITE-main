package com.ayang.website.websocket.service;

import io.netty.channel.Channel;

/**
 * @author shy
 * @date 2023/12/6
 * @description WebStocketService
 */
public interface WebsocketService {

    /**
     * 新增连接
     */
    void connect(Channel channel);

    /**
     * 登录认证
     */
    void handlerLoginReq(Channel channel);

    void remove(Channel channel);

    void scanLoginSuccess(Integer code, Long id);

    void waitAuthorize(Integer code);

    void authorize(Channel channel, String token);
}
