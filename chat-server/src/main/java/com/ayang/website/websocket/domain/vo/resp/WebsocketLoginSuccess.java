package com.ayang.website.websocket.domain.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shy
 * @date 2023/12/5
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketLoginSuccess {
    private Long uid;
    private String avatar;
    private String token;
    private String name;
    //用户权限 0普通用户 1超管
    private Integer power;
}
