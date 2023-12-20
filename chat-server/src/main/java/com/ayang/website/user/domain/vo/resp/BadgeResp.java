package com.ayang.website.user.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shy
 * @date 2023/12/19
 * @description BadgeResp
 */
@Data
@ApiModel(value = "BadgeResp对象", description = "徽章信息返回")
public class BadgeResp {

    @ApiModelProperty(value = "徽章id")
    private Long id;

    @ApiModelProperty(value = "徽章图标")
    private String img;

    @ApiModelProperty(value = "徽章描述")
    private String describe;

    @ApiModelProperty(value = "是否拥有徽章 0.否 1.是")
    private Integer obtain;

    @ApiModelProperty(value = "是否佩戴 0.否 1.是")
    private Integer wearing;
}
