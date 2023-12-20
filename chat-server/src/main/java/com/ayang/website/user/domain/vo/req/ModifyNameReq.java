package com.ayang.website.user.domain.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author shy
 * @date 2023/12/19
 * @description ModifyNameReq
 */
@Data
public class ModifyNameReq {

    @ApiModelProperty(value = "用户名")
    @NotBlank
    @Length(max = 6, message = "用户名不可以超过6位")
    private String name;
}
