package com.ayang.website.websocket;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.ayang.website.websocket.domain.enums.WebsocketReqTypeEnum;
import com.ayang.website.websocket.domain.vo.req.WebsocketBaseReq;
import com.ayang.website.websocket.service.WebsocketService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shy
 * @date 2023/12/4
 */
@Slf4j
@Sharable
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private WebsocketService websocketService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        websocketService = SpringUtil.getBean(WebsocketService.class);
        websocketService.connect(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        userOffline(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            String token = NettyUtil.getAttr(ctx.channel(), NettyUtil.TOKEN);
            if (StringUtils.isNotBlank(token)) {
                websocketService.authorize(ctx.channel(), token);
            }
        } else if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲");
                // 用户下线
                userOffline(ctx.channel());
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("exceptionCaught", cause);
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 用户下线统一处理
     */
    private void userOffline(Channel channel) {
        websocketService.remove(channel);
        channel.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        WebsocketBaseReq wsBaseReq = JSONUtil.toBean(text, WebsocketBaseReq.class);
        switch (WebsocketReqTypeEnum.of(wsBaseReq.getType())) {
            case LOGIN:
                websocketService.handlerLoginReq(ctx.channel());
                break;
            case HEARTBEAT:
                break;
            case AUTHORIZE:
                websocketService.authorize(ctx.channel(), wsBaseReq.getDate());
                break;
            default:
                break;
        }
    }
}
