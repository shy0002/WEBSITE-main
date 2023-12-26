package com.ayang.website.common.event;

import com.ayang.website.user.domain.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author shy
 * @date 2023/12/25
 * @description 用户拉黑事件
 */
@Getter
public class UseBlackEvent extends ApplicationEvent {
    private User user;

    public UseBlackEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
