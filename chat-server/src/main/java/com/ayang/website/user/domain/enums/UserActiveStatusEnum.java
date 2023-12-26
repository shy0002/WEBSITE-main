package com.ayang.website.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 幂等来源枚举
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/20
 **/
@AllArgsConstructor
@Getter
public enum UserActiveStatusEnum {
    ONLINE(1, "在线"),
    OFFLINE(2,"离线"),
    ;
    private final Integer status;
    private final String desc;
}
