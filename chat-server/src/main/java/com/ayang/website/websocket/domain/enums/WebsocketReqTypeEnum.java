package com.ayang.website.websocket.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author shy
 * @date 2023/12/5
 */
@AllArgsConstructor
@Getter
public enum WebsocketReqTypeEnum {
    LOGIN(1, "请求登录二维码"),
    HEARTBEAT(2, "心跳包"),
    AUTHORIZE(3, "登录认证"),
    ;

    private final Integer type;
    private final String desc;

    private static Map<Integer, WebsocketReqTypeEnum> cache;

    static {
        cache = Arrays.stream(WebsocketReqTypeEnum.values()).collect(Collectors.toMap(WebsocketReqTypeEnum::getType, Function.identity()));
    }

    public static WebsocketReqTypeEnum of(Integer type) {
        return cache.get(type);
    }
}
