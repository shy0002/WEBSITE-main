package com.ayang.website.user.controller;


import com.ayang.website.user.domain.vo.resp.UserInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
@RestController
@RequestMapping("/cApi/user")
@RequiredArgsConstructor
@Api(value = "用户相关接口")
public class UserController {

    @GetMapping("/userInfo")
    @ApiOperation("获取用户个人信息")
    public UserInfoResp getUserInfo(@RequestParam Long uid){

        return null;
    }
}

