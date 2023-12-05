package com.ayang.website.websocket.domain.vo.req;

import lombok.Data;

/**
 * @author shy
 * @date 2023/12/5
 */
@Data
public class WebsocketBaseReq {
    /**
     * 数据类型
     * @see com.ayang.website.websocket.domain.enums.WebsocketReqTypeEnum
     */
    private Integer type;

    /**
     * 数据
     */
    private String date;
}
