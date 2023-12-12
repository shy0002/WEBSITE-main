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
    void hanlerLoginReq(Channel channel);

    void remove(Channel channel);

    void scanLoginSussecc(Integer code, Long id);

    void waitAuthorize(Integer code);
}
