package com.ayang.website.user.service;

import com.ayang.website.user.domain.entity.Role;
import com.ayang.website.user.domain.enums.RoleEnum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-26
 */
public interface IRoleService{

    /**
     * 是否拥有某个权限(临时写法)
     * @param uid 用户id
     * @param roleEnum 角色枚举
     * @return 是否拥有权限
     */
    boolean hasPower(Long uid, RoleEnum roleEnum);

}
