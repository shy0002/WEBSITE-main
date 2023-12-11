package com.ayang.website.user.service.adapter;

import com.ayang.website.user.domain.entity.User;

/**
 * @author shy
 * @date 2023/12/11
 * @description UserAdapter
 */
public class UserAdapter {
    public static User buildUserSave(String openId) {
        return User.builder().openId(openId).build();
    }
}
