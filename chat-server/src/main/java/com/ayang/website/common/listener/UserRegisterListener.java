package com.ayang.website.common.listener;

import com.ayang.website.common.event.UserRegisterEvent;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.enums.IdempotentEnum;
import com.ayang.website.user.domain.enums.ItemEnum;
import com.ayang.website.user.service.UserBackpackService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @description: 用户注册监听
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/23
 **/
@Component
@RequiredArgsConstructor
public class UserRegisterListener {

    private final UserBackpackService userBackpackService;
    private final UserDao userDao;


    @Async
    @TransactionalEventListener(classes = UserRegisterEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void sendCard(UserRegisterEvent event) {
        User user = event.getUser();
        userBackpackService.acquireItem(user.getId(), ItemEnum.MODIFY_NAME_CARD.getId(), IdempotentEnum.UID, user.getId().toString());
    }


    @Async
    @TransactionalEventListener(classes = UserRegisterEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void sendBadge(UserRegisterEvent event) {
        // 前100名用户注册徽章
        User user = event.getUser();
        int registeredCount = userDao.count();
        if (registeredCount < 10){
            userBackpackService.acquireItem(user.getId(), ItemEnum.REG_TOP10_BADGE.getId(), IdempotentEnum.UID, user.getId().toString());
        }else if (registeredCount <100){
            userBackpackService.acquireItem(user.getId(), ItemEnum.REG_TOP100_BADGE.getId(), IdempotentEnum.UID, user.getId().toString());
        }
    }

}
