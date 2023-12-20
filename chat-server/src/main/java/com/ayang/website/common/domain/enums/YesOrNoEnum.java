package com.ayang.website.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shy
 * @date 2023/12/19
 * @description YesOrNoEnum
 */
@AllArgsConstructor
@Getter
public enum YesOrNoEnum {
    NO(0, "否"),
    YES(1,"是");
    private final Integer status;
    private final String desc;

}
