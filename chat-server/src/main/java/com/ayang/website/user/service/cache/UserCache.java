package com.ayang.website.user.service.cache;

import com.ayang.website.user.dao.BlackDao;
import com.ayang.website.user.dao.UserRoleDao;
import com.ayang.website.user.domain.entity.Black;
import com.ayang.website.user.domain.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final BlackDao blackDao;

    @Cacheable(cacheNames = "user", key = "'roles:'+#uid")
    public Set<Long> getRoleSet(Long uid) {
        List<UserRole> userRoles = userRoleDao.listByUid(uid);
        return userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toSet());
    }

    @Cacheable(cacheNames = "user", key = "'blackList'")
    public Map<Integer, Set<String>> getBlackCacheMap() {
        Map<Integer, List<Black>> collect = blackDao.list().stream().collect(Collectors.groupingBy(Black::getType));
        Map<Integer, Set<String>> result = new HashMap<>();
        collect.forEach((type, list)-> result.put(type, list.stream().map(Black::getTarget).collect(Collectors.toSet())));
        return result;
    }

    @CacheEvict(cacheNames = "user", key = "'blackList'")
    public Map<Integer, Set<String>> evictBlackMap() {
        return null;
    }

}
