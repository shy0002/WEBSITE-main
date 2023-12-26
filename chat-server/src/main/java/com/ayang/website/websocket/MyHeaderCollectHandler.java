package com.ayang.website.websocket;

import cn.hutool.core.net.url.UrlBuilder;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import jodd.util.StringUtil;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * @description: 头信息采集程序
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/18
 **/

public class MyHeaderCollectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            UrlBuilder urlBuilder = UrlBuilder.ofHttp(req.uri());
            Optional<String> tokenOptional = Optional.of(urlBuilder)
                    .map(UrlBuilder::getQuery)
                    .map(k -> k.get("token"))
                    .map(CharSequence::toString);
            // 如果token存在
            tokenOptional.ifPresent(s -> NettyUtil.setAttr(ctx.channel(), NettyUtil.TOKEN, s));
            // 移除后面拼接的所有参数
            req.setUri(urlBuilder.getPathStr());
            // 获取用户ip
            String ip = req.headers().get("X-Real_IP");
            if (StringUtils.isBlank(ip)){
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                ip = address.getAddress().getHostAddress();
            }
            // 保存到channel附件
            NettyUtil.setAttr(ctx.channel(), NettyUtil.IP, ip);
            // 处理程序只需要执行一次
            ctx.pipeline().remove(this);
        }
        ctx.fireChannelRead(msg);
    }
}
