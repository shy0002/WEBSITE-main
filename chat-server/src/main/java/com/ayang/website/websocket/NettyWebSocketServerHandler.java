package com.ayang.website.websocket;

import cn.hutool.json.JSONUtil;
import com.ayang.website.websocket.domain.enums.WebsocketReqTypeEnum;
import com.ayang.website.websocket.domain.vo.req.WebsocketBaseReq;
import io.netty.channel.ChannelHandler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shy
 * @date 2023/12/4
 */
@Slf4j
@Sharable
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            System.out.println("握手完成");
        }else if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE){
                System.out.println("读空闲");
                // TODO 用户下线
                ctx.channel().close();
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        WebsocketBaseReq wsBaseReq = JSONUtil.toBean(text, WebsocketBaseReq.class);
        switch (WebsocketReqTypeEnum.of(wsBaseReq.getType())) {
            case LOGIN:
                System.out.println("请求二维码");
                break;
            case HEARTBEAT:
                break;
            case AUTHORIZE:
                break;
            default:
                break;
        }
    }
}
