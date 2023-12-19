package com.ayang.website.user.service;

import com.ayang.website.user.domain.entity.User;

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
}
