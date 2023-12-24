package com.ayang.website.common.aspect;

import com.ayang.website.common.annotaion.RedissonLock;
import com.ayang.website.common.service.LockService;
import com.ayang.website.common.utils.SpElUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 分布式锁切面
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/23
 **/
@Component
@Aspect
@Order(0) // 确保比事务注解先执行，分布式锁在事务外
@RequiredArgsConstructor
public class RedissonLockAspect {

    private final LockService lockService;

    @Around("@annotation(redissonLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedissonLock redissonLock) throws Throwable{
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String prefix = StringUtils.isBlank(redissonLock.prefixKey()) ? SpElUtils.getMethodKey(method) :redissonLock.prefixKey();
        String key = SpElUtils.parseSpEl(method, joinPoint.getArgs(),redissonLock.key());
        return lockService.executeWithLock(prefix +":"+ key,redissonLock.waitTime(),redissonLock.unit(),joinPoint::proceed);
    }
}
