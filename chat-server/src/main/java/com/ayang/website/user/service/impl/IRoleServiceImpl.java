package com.ayang.website.user.service.impl;

import com.ayang.website.user.domain.enums.RoleEnum;
import com.ayang.website.user.service.IRoleService;
import com.ayang.website.user.service.cache.UserCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @description: 角色业务逻辑实现类
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/26
 **/
@Service
@RequiredArgsConstructor
public class IRoleServiceImpl implements IRoleService {

    private final UserCache userCache;

    @Override
    public boolean hasPower(Long uid, RoleEnum roleEnum) {
        Set<Long> roleSet = userCache.getRoleSet(uid);
        return isAdmin(roleSet) || roleSet.contains(roleEnum.getId());
    }

    private boolean isAdmin(Set<Long> roleSet) {
        return roleSet.contains(RoleEnum.ADMIN.getId());
    }
}
