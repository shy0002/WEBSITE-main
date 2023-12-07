package com.ayang.website.websocket.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ayang.website.websocket.domain.dto.WebsocketChannelExtraDTO;
import com.ayang.website.websocket.domain.enums.WebsocketRespTypeEnum;
import com.ayang.website.websocket.domain.vo.resp.WebsocketBaseResp;
import com.ayang.website.websocket.domain.vo.resp.WebsocketLoginUrl;
import com.ayang.website.websocket.service.WebsocketService;
import com.ayang.website.websocket.service.adapter.WebsocketAdapter;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.SneakyThrows;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shy
 * @date 2023/12/6
 * @description websocket逻辑管理
 */
@Service
public class WebsocketServiceImpl implements WebsocketService {
    @Autowired
    private WxMpService wxMpService;
    /**
     * 在线用户连接管理(包括登录态/游客)
     */
    private static final ConcurrentHashMap<Channel, WebsocketChannelExtraDTO> ONLINE_WS_MAP= new ConcurrentHashMap<>();

    public static final Duration DURATION = Duration.ofHours(1);
    public static final int MAXIMUM_SIZE = 10000;
    /**
     * 临时保存登录code和channel的映射关系
     */
    private static final Cache<Integer, Channel> WAIT_LOGIN_MAP = Caffeine.newBuilder()
            .maximumSize(MAXIMUM_SIZE)
            .expireAfterWrite(DURATION)
            .build();

    @Override
    public void connect(Channel channel) {
        ONLINE_WS_MAP.put(channel, new WebsocketChannelExtraDTO());
    }

    @SneakyThrows
    @Override
    public void hanlerLoginReq(Channel channel) {
        // 生成随机码
        Integer code = generateLoginCode(channel);
        // 找微信申请带参二维码
        WxMpQrCodeTicket wxMpQrCodeTicket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(code, (int) DURATION.getSeconds());
        // 将二维码推送给前端
        sendMsg(channel, WebsocketAdapter.buildResp(wxMpQrCodeTicket));
    }

    @Override
    public void remove(Channel channel) {
        ONLINE_WS_MAP.remove(channel);
        // todo 用户下线
    }

    private void sendMsg(Channel channel, WebsocketBaseResp<?> resp) {
        channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(resp)));
    }

    private Integer generateLoginCode(Channel channel) {
        Integer code;
        do {
            code = RandomUtil.randomInt(Integer.MAX_VALUE);
        }while (Objects.nonNull(WAIT_LOGIN_MAP.asMap().putIfAbsent(code, channel)));
        return code;
    }
}
