package com.ayang.website.user.service.impl;

import com.ayang.website.user.dao.UserBackpackDao;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.enums.ItemEnum;
import com.ayang.website.user.domain.vo.req.ModifyNameReq;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;
import com.ayang.website.user.service.UserService;
import com.ayang.website.user.service.adapter.UserAdapter;
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
    private final UserBackpackDao userBackpackDao;

    @Override
    @Transactional
    public Long register(User insert) {
        userDao.save(insert);
        // todo 用户注册的事件
        return insert.getId();
    }

    @Override
    public UserInfoResp getUserInfo(Long uid) {
        User user = userDao.getById(uid);
        Integer modifyNameCount = userBackpackDao.getCountByValidItemId(uid, ItemEnum.MODIFY_NAME_CARD.getId());
        return UserAdapter.buildUserInfo(user, modifyNameCount);
    }

    @Override
    public void cmodifyName(Long uid, ModifyNameReq req) {
        if (req.getName().length() > 6){

        }
    }
}
