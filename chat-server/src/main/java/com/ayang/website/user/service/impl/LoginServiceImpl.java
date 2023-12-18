package com.ayang.website.user.service.impl;

import com.ayang.website.common.constant.RedisKey;
import com.ayang.website.common.utils.JwtUtils;
import com.ayang.website.common.utils.RedisUtils;
import com.ayang.website.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author shy
 * @date 2023/12/12
 * @description LoginServiceImpl
 */
@Service
public class LoginServiceImpl implements LoginService {
    public static final int TOKEN_EXPIRE_DAYS = 3;
    public static final int TOKEN_RENEWAL_DAYS = 1;
    public static final int TOKEN_EXPIRE_NULL = -2;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean verify(String token) {
        return false;
    }

    @Override
    public void renewalTokenIfNecessary(String token) {
        Long uid = getValidUid(token);
        String userTokenKey = getUserToken(uid);
        Long expireDays = RedisUtils.getExpire(userTokenKey, TimeUnit.DAYS);
        if (expireDays == TOKEN_EXPIRE_NULL) {
            return;
        }
        if (expireDays < TOKEN_RENEWAL_DAYS) {
            RedisUtils.expire(getUserToken(uid), TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        }

    }

    @Override
    public String login(Long uid) {
        String token = jwtUtils.createToken(uid);
        RedisUtils.set(getUserToken(uid), token, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        return token;
    }

    private String getUserToken(Long uid) {
        return RedisKey.getKey(RedisKey.USER_TOKEN, uid);
    }

    @Override
    public Long getValidUid(String token) {
        Long uid = jwtUtils.getUidOrNull(token);
        if (Objects.isNull(uid)) {
            return null;
        }
        String oldToken = RedisUtils.get(getUserToken(uid));
        return Objects.equals(token, oldToken) ? uid : null;
    }
}
