package com.ayang.website.common.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ayang.website.common.domain.dto.RequestInfo;
import com.ayang.website.common.exception.HttpErrorEnum;
import com.ayang.website.common.utils.RequestHolder;
import com.ayang.website.user.domain.enums.BlackTypeEnum;
import com.ayang.website.user.service.cache.UserCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author shy
 * @date 2023/12/19
 * @description 拉黑拦截器
 */
@Component
@RequiredArgsConstructor
public class BlackInterceptor implements HandlerInterceptor {
    private final UserCache userCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<Integer, Set<String>> blackMap = userCache.getBlackCacheMap();
        RequestInfo requestInfo = RequestHolder.get();
        if (inBlackList(requestInfo.getUid(), blackMap.get(BlackTypeEnum.UID.getType()))) {
            HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
            return false;
        }
        if (inBlackList(requestInfo.getIp(), blackMap.get(BlackTypeEnum.IP.getType()))) {
            HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
            return false;
        }
        return true;
    }

    private boolean inBlackList(Object target, Set<String> set) {
        if (Objects.isNull(target)|| CollUtil.isEmpty(set)){
            return false;
        }
        return set.contains(target.toString());

    }
}
