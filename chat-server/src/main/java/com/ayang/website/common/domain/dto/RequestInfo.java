package com.ayang.website.common.domain.dto;

import lombok.Data;

/**
 * @author shy
 * @date 2023/12/19
 * @description 用户请求信息
 */
@Data
public class RequestInfo {
    private Long uid;
    private String ip;
}
