package com.ayang.website.user.controller;


import com.ayang.website.common.domain.vo.resp.ApiResult;
import com.ayang.website.common.utils.AssertUtil;
import com.ayang.website.common.utils.RequestHolder;
import com.ayang.website.user.domain.enums.RoleEnum;
import com.ayang.website.user.domain.vo.req.BlackReq;
import com.ayang.website.user.domain.vo.req.ModifyNameReq;
import com.ayang.website.user.domain.vo.req.WearingBadgeReq;
import com.ayang.website.user.domain.vo.resp.BadgeResp;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;
import com.ayang.website.user.service.IRoleService;
import com.ayang.website.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-05
 */
@RestController
@RequestMapping("/capi/user")
@RequiredArgsConstructor
@Api(tags = "用户相关接口")
public class UserController {

    private final UserService userService;
    private final IRoleService iRoleService;

    @GetMapping("/userInfo")
    @ApiOperation("获取用户个人信息")
    public ApiResult<UserInfoResp> getUserInfo() {
        return ApiResult.success(userService.getUserInfo(RequestHolder.get().getUid()));
    }

    @PutMapping("/name")
    @ApiOperation("修改用户名")
    public ApiResult<Void> modifyName(@Valid @RequestBody ModifyNameReq req) {
        userService.modifyName(RequestHolder.get().getUid(), req.getName());
        return ApiResult.success();
    }

    @GetMapping("/badges")
    @ApiOperation("可选徽章列表")
    public ApiResult<List<BadgeResp>> badges() {
        return ApiResult.success(userService.badges(RequestHolder.get().getUid()));
    }

    @PutMapping("/badge")
    @ApiOperation("佩戴徽章")
    public ApiResult<Void> wearingBadge(@Valid @RequestBody WearingBadgeReq req) {
        userService.wearingBadge(RequestHolder.get().getUid(), req.getItemId());
        return ApiResult.success();
    }

    @PutMapping("/black")
    @ApiOperation("拉黑用户")
    public ApiResult<Void> black(@Valid @RequestBody BlackReq req) {
        Long uid = RequestHolder.get().getUid();
        boolean hasPower = iRoleService.hasPower(uid, RoleEnum.ADMIN);
        AssertUtil.isTrue(hasPower, "管理员没有拉黑用户权限");
        userService.black(req);
        return ApiResult.success();
    }

}