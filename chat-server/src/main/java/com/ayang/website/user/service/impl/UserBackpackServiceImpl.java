package com.ayang.website.user.service.impl;

import com.ayang.website.common.annotaion.RedissonLock;
import com.ayang.website.common.domain.enums.YesOrNoEnum;
import com.ayang.website.common.service.LockService;
import com.ayang.website.user.dao.UserBackpackDao;
import com.ayang.website.user.domain.entity.UserBackpack;
import com.ayang.website.user.domain.enums.IdempotentEnum;
import com.ayang.website.user.service.UserBackpackService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description: 用户背包业务逻辑实现类
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/20
 **/
@Service
public class UserBackpackServiceImpl implements UserBackpackService {
    private final UserBackpackDao userBackpackDao;
    private final UserBackpackServiceImpl userBackpackService;

    @Autowired
    public UserBackpackServiceImpl(UserBackpackDao userBackpackDao,
                                   @Lazy UserBackpackServiceImpl userBackpackService){
        this.userBackpackDao = userBackpackDao;
        this.userBackpackService = userBackpackService;
    }



    @Override
    public void acquireItem(Long uid, Long itemId, IdempotentEnum idempotentEnum, String businessId) {
        String idempotent = getIdempotent(itemId, idempotentEnum, businessId);
        userBackpackService.doAcquireItem(uid, itemId, idempotent);
    }

    @RedissonLock(key = "#idempotent", waitTime = 5000)
    public void doAcquireItem(Long uid, Long itemId, String idempotent) {
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
    }

    private String getIdempotent(Long itemId, IdempotentEnum idempotentEnum, String businessId) {
        return String.format("%d_%d_%s", itemId, idempotentEnum.getType(), businessId);
    }
}
