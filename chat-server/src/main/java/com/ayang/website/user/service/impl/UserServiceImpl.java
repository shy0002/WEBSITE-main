package com.ayang.website.user.service.impl;

import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shy
 * @date 2023/12/11
 * @description UserServiceImpl
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @Override
    @Transactional
    public Long register(User insert) {
        userDao.save(insert);
        // todo 用户注册的事件
        return insert.getId();
    }
}
