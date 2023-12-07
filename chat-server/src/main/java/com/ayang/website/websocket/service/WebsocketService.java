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
     * @param channel
     */
    void connect(Channel channel);
}
