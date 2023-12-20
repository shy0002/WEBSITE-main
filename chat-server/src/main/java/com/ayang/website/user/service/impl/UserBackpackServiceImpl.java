package com.ayang.website.user.service.impl;

import com.ayang.website.common.domain.enums.YesOrNoEnum;
import com.ayang.website.common.utils.AssertUtil;
import com.ayang.website.user.dao.UserBackpackDao;
import com.ayang.website.user.domain.entity.UserBackpack;
import com.ayang.website.user.domain.enums.IdempotentEnum;
import com.ayang.website.user.service.UserBackpackService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description: 用户背包业务逻辑实现类
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/20
 **/
@Service
@RequiredArgsConstructor
public class UserBackpackServiceImpl implements UserBackpackService {
    private final RedissonClient redissonClient;
    private final UserBackpackDao userBackpackDao;

    @Override
    public void acquireItem(Long uid, Long itemId, IdempotentEnum idempotentEnum, String businessId) {
        String idempotent = getIdempotent(itemId, idempotentEnum, businessId);
        RLock lock = redissonClient.getLock("acquireItem" + idempotent);
        boolean b = lock.tryLock();
        AssertUtil.isTrue(b, "请求太频繁了");
        try {
            UserBackpack userBackpack = userBackpackDao.getByIdempotent(idempotent);
            if (Objects.nonNull(userBackpack)) {
                return;
            }
            // 发放物品
            UserBackpack insert = UserBackpack.builder()
                    .uid(uid)
                    .itemId(itemId)
                    .status(YesOrNoEnum.NO.getStatus())
                    .idempotent(idempotent)
                    .build();
            userBackpackDao.save(insert);
        } finally {
            lock.unlock();
        }
    }

    private String getIdempotent(Long itemId, IdempotentEnum idempotentEnum, String businessId) {
        return String.format("%d_%d_%s", itemId, idempotentEnum.getType(), businessId);
    }
}
