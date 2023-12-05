package com.ayang.website.websocket.domain.enums;

import com.ayang.website.websocket.domain.vo.resp.*;
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
public enum WebsocketRespTypeEnum {
    LOGIN_URL(1, "登录二维码返回", WebsocketLoginUrl.class),
    LOGIN_SCAN_SUCCESS(2, "用户扫描成功等待授权", null),
    LOGIN_SUCCESS(3, "用户登录成功返回用户信息", WebsocketLoginSuccess.class),
    MESSAGE(4, "新消息", WebsocketMessage.class),
    ONLINE_OFFLINE_NOTIFY(5, "上下线通知", WebsocketOnlineOfflineNotify.class),
    INVALIDATE_TOKEN(6, "使前端的token失效，意味着前端需要重新登录", null),
    BLACK(7, "拉黑用户", WebsocketBlack.class),
    MARK(8, "消息标记", WebsocketMsgMark.class),
    RECALL(9, "消息撤回", WebsocketMsgRecall.class),
    APPLY(10, "好友申请", WebsocketFriendApply.class),
    MEMBER_CHANGE(11, "成员变动", WebsocketMemberChange.class),
    ;

    private final Integer type;
    private final String desc;
    private final Class dataClass;

    private static Map<Integer, WebsocketRespTypeEnum> cache;

    static {
        cache = Arrays.stream(WebsocketRespTypeEnum.values()).collect(Collectors.toMap(WebsocketRespTypeEnum::getType, Function.identity()));
    }

    public static WebsocketRespTypeEnum of(Integer type) {
        return cache.get(type);
    }
}
