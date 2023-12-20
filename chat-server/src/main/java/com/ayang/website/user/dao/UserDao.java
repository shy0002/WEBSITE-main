package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 数据库交互服务
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
@Repository
public class UserDao extends ServiceImpl<UserMapper, User> {

    public User getByOpenId(String openId) {
        return lambdaQuery().eq(User::getOpenId, openId).one();
    }

    public User getByName(String name) {
        return lambdaQuery().eq(User::getName, name).one();
    }

    public void modifyName(Long uid, String name) {
        lambdaUpdate()
                .eq(User::getId, uid)
                .set(User::getName, name)
                .update();
    }

    public void wearingBadge(Long uid, Long itemId) {
        lambdaUpdate()
                .eq(User::getId, uid)
                .set(User::getItemId, itemId)
                .update();
    }
}
