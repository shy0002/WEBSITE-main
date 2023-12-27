package com.ayang.website.common.event.listener;

import com.ayang.website.common.event.UseBlackEvent;
import com.ayang.website.common.event.UserOnlineEvent;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.enums.UserActiveStatusEnum;
import com.ayang.website.user.service.IpService;
import com.ayang.website.user.service.cache.UserCache;
import com.ayang.website.websocket.service.WebsocketService;
import com.ayang.website.websocket.service.adapter.WebsocketAdapter;
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
public class UserBlackListener {

    private final UserDao userDao;
    private final WebsocketService websocketService;
    private final UserCache userCache;

    @Async
    @TransactionalEventListener(classes = UseBlackEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void sendMsg(UseBlackEvent event) {
        User user = event.getUser();
        websocketService.sendMsgToAll(WebsocketAdapter.buildBlack(user));
    }

    @Async
    @TransactionalEventListener(classes = UseBlackEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void changeUserStatus(UseBlackEvent event) {
        userDao.invalidUid(event.getUser().getId());
    }

    @Async
    @TransactionalEventListener(classes = UseBlackEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void evictCache(UseBlackEvent event) {
        userCache.evictBlackMap();
    }

}
