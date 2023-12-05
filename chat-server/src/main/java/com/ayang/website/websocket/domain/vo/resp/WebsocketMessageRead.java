package com.ayang.website.websocket.domain.vo.resp;

import io.swagger.annotations.ApiModelProperty;
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
public class WebsocketMessageRead {
    @ApiModelProperty("消息")
    private Long msgId;
    @ApiModelProperty("阅读人数（可能为0）")
    private Integer readCount;
}
