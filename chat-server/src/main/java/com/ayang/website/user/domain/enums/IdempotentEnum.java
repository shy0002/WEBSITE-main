package com.ayang.website.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @description: 幂等来源枚举
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/20
 **/
@AllArgsConstructor
@Getter
public enum IdempotentEnum {
    UID(1, "uid"),
    MSG_ID(2,"消息di"),
    ;
    private final Integer type;
    private final String desc;
}
