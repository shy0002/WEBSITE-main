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
public class WebsocketLoginUrl {
    private String loginUrl;
}
