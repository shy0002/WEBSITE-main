package com.ayang.website.user.service.cache;

import com.ayang.website.user.dao.UserRoleDao;
import com.ayang.website.user.domain.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shy
 * @date 2023/12/20
 * @description ItemCache
 */
@Component
@RequiredArgsConstructor
public class UserCache {

    private final UserRoleDao userRoleDao;

    @CacheEvict(cacheNames = "user", key = "'roles:'+#uid")
    public Set<Long> getRoleSet(Long uid) {
        List<UserRole> userRoles = userRoleDao.listByUid(uid);
        return userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toSet());
    }
}
