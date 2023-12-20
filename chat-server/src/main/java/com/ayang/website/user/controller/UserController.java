package com.ayang.website.user.controller;


import com.ayang.website.common.domain.dto.RequestInfo;
import com.ayang.website.common.domain.vo.resp.ApiResult;
import com.ayang.website.common.utils.RequestHolder;
import com.ayang.website.user.domain.vo.req.ModifyNameReq;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;
import com.ayang.website.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
@RestController
@RequestMapping("/capi/user")
@RequiredArgsConstructor
@Api(tags = "用户相关接口")
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation("获取用户个人信息")
    public ApiResult<UserInfoResp> getUserInfo() {
        return ApiResult.success(userService.getUserInfo(RequestHolder.get().getUid()));
    }

    @PutMapping("/name")
    @ApiOperation("修改用户名")
    public ApiResult<Void> modifyName(@Valid @RequestBody ModifyNameReq req) {
        userService.cmodifyName(RequestHolder.get().getUid(), req);
        return ApiResult.success();
    }
}

