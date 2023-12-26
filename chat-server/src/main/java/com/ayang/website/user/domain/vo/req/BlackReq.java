package com.ayang.website.user.domain.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author shy
 * @date 2023/12/19
 * @description 拉黑用户请求体
 */
@Data
public class BlackReq {

    @ApiModelProperty("用户id")
    @NotNull
    private Long uid;
}
