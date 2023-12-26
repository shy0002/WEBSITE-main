package com.ayang.website.user.service;

import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.vo.req.BlackReq;
import com.ayang.website.user.domain.vo.resp.BadgeResp;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 用户id
     */
    Long register(User user);

    /**
     * 获取用户信息
     *
     * @param uid 用户id
     * @return 用户信息响应
     */
    UserInfoResp getUserInfo(Long uid);

    /**
     * 修改名称
     *
     * @param uid  用户id
     * @param name 新名称
     */
    void modifyName(Long uid, String name);

    /**
     * 获取所有的徽章列表
     *
     * @param uid 用户id
     * @return 徽章信息列表
     */
    List<BadgeResp> badges(Long uid);

    /**
     * 佩戴徽章
     *
     * @param uid    用户id
     * @param itemId 徽章id
     */
    void wearingBadge(Long uid, Long itemId);

    /**
     * 拉黑用户
     * @param req 拉黑请求体
     */
    void black(BlackReq req);
}
