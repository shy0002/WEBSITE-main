package com.ayang.website.websocket;

import com.ayang.website.web.service.P2PService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * p2p客户端
 *
 * @author Jared Jia1·
 */
@Component
@Slf4j
public class P2PClient {

    @Autowired
    @Lazy
    P2PService p2pService;

    public void connectToPeer(String addr) {
        try {
            final WebSocketClient socketClient = new WebSocketClient(new URI(addr)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    //客户端发送请求，查询最新区块
                    p2pService.write(this, p2pService.queryLatestBlockMsg());
                    p2pService.getSockets().add(this);
                }

                /**
                 * 接收到消息时触发
                 * @param msg
                 */
                @Override
                public void onMessage(String msg) {
                    p2pService.handleMessage(this, msg, p2pService.getSockets());
                }

                @Override
                public void onClose(int i, String msg, boolean b) {
                    p2pService.getSockets().remove(this);
                    System.out.println("connection closed");
                }

                @Override
                public void onError(Exception e) {
                    p2pService.getSockets().remove(this);
                    System.out.println("connection failed ");
                    log.error("连接错误{0}",e);
                }
            };
            socketClient.connect();
        } catch (URISyntaxException e) {
            System.out.println("p2p connect is error:" + e.getMessage());
        }
    }

}
