package com.ayang.website.websocket.service.impl;

import com.ayang.website.websocket.domain.dto.WebsocketChannelExtraDTO;
import com.ayang.website.websocket.service.WebsocketService;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shy
 * @date 2023/12/6
 * @description websocket逻辑管理
 */
@Service
public class WebsocketServiceImpl implements WebsocketService {

    private static final ConcurrentHashMap<Channel, WebsocketChannelExtraDTO> ONLINE_WS_MAP= new ConcurrentHashMap<>();

    @Override
    public void connect(Channel channel) {

    }
}
