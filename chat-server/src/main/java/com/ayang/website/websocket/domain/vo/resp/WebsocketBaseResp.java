package com.ayang.website.websocket.domain.vo.resp;

import lombok.Data;

/**
 * @author shy
 * @date 2023/12/5
 */
@Data
public class WebsocketBaseResp<T> {

    /**
     * 数据类型
     * @see com.ayang.website.websocket.domain.enums.WebsocketRespTypeEnum
     */
    private Integer type;

    /**
     * 数据
     */
    private T date;
}
