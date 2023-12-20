package com.ayang.website.user.domain.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author shy
 * @date 2023/12/19
 * @description ModifyNameReq
 */
@Data
public class WearingBadgeReq {

    @ApiModelProperty("徽章id")
    @NotNull
    private Long itemId;
}
