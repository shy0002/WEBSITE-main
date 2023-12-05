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
public class WebsocketFriendApply {
    @ApiModelProperty("申请人")
    private Long uid;
    @ApiModelProperty("申请未读数")
    private Integer unreadCount;
}
