package com.ayang.website.common.event;

import com.ayang.website.user.domain.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @description: 用户注册事件
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/23
 **/
@Getter
public class UserRegisterEvent extends ApplicationEvent {
    private User user;

    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
