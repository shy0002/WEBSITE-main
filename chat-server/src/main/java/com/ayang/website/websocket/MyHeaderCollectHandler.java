package com.ayang.website.websocket;

import cn.hutool.core.net.url.UrlBuilder;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.util.Optional;

/**
 * @description: 重写握手协议
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/18
 **/

public class MyHeaderCollectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpObject) {
            HttpObject httpObject = (HttpObject) msg;
            if (httpObject instanceof HttpRequest) {
                HttpRequest req = (HttpRequest) httpObject;
                UrlBuilder urlBuilder = UrlBuilder.ofHttp(req.uri());
                Optional<String> tokenOptional = Optional.of(urlBuilder)
                        .map(UrlBuilder::getQuery)
                        .map(k -> k.get("token"))
                        .map(CharSequence::toString);

                tokenOptional.ifPresent(s -> NettyUtil.setAttr(ctx.channel(), NettyUtil.TOKEN, s));
                req.setUri(urlBuilder.getPathStr());
            }
        }
        ctx.fireChannelRead(msg);
    }
}
