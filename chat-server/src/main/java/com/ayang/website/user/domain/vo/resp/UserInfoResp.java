package com.ayang.website.user.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shy
 * @date 2023/12/19
 * @description UserInfoResp
 */
@Data
@ApiModel(value = "UserInfoResp对象", description = "用户信息返回")
public class UserInfoResp {

    @ApiModelProperty(value = "uid")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户性别")
    private String sex;

    @ApiModelProperty(value = "用户改名卡次数")
    private Integer modifyNameChance;
}
