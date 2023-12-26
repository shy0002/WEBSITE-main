package com.ayang.website.common.event.listener;

import com.ayang.website.common.event.UserOnlineEvent;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.enums.UserActiveStatusEnum;
import com.ayang.website.user.service.IpService;
import com.ayang.website.user.service.UserBackpackService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @description: 用户上线事件监听
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/23
 **/
@Component
@RequiredArgsConstructor
public class UserOnlineListener {

    private final IpService ipService;
    private final UserDao userDao;


    @Async
    @TransactionalEventListener(classes = UserOnlineEvent.class, phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void userOnline(UserOnlineEvent event) {
        User user = event.getUser();
        User update = new User();
        update.setId(user.getId());
        update.setLastOptTime(user.getLastOptTime());
        update.setIpInfo(user.getIpInfo());
        update.setActiveStatus(UserActiveStatusEnum.ONLINE.getStatus());
        userDao.updateById(update);
        // 用户ip详情的解析
        ipService.refreshIpDetailAsync(user.getId());
    }

}
