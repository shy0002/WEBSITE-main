package com.ayang.website.websocket.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author shy
 * @date 2023/12/6
 * @description 记录和前端连接的一些映射信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketChannelExtraDTO {
    /**
     * 前端如果登录了，记录uid
     */
    private Long uid;
}
