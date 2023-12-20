package com.ayang.website.user.service;

import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.vo.req.ModifyNameReq;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
public interface UserService {

    Long register(User insert);

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return 用户信息响应
     */
    UserInfoResp getUserInfo(Long uid);

    void cmodifyName(Long uid, ModifyNameReq req);
}
