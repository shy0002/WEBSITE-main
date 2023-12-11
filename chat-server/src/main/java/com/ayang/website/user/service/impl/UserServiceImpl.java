package com.ayang.website.user.service.impl;

import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shy
 * @date 2023/12/11
 * @description UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public Long register(User insert) {
        userDao.save(insert);
        // todo 用户注册的事件
        return insert.getId();
    }
}
