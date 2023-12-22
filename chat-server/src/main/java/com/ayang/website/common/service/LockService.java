package com.ayang.website.common.service;

import com.ayang.website.common.exception.BusinessException;
import com.ayang.website.common.exception.CommonErrorEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author shy
 * @date 2023/12/22
 * @description LockService
 */
@Service
@RequiredArgsConstructor
public class LockService {
    private final RedissonClient redissonClient;

    @SneakyThrows
    public <T> T executeWithLock(String key, int waitTime, TimeUnit timeUnit, Supplier<T> supplier){
        RLock lock = redissonClient.getLock(key);
        boolean success = lock.tryLock(waitTime, timeUnit);
        if (!success){
            throw new BusinessException(CommonErrorEnum.LOCK_LIMIT);
        }
        try {
            return supplier.get();
        }finally {
            lock.unlock();
        }
    }


    public <T> T executeWithLock(String key, Runnable runnable){
        return executeWithLock(key, -1, TimeUnit.MILLISECONDS, () ->{
            runnable.run();
            return null;
        });
    }
}
