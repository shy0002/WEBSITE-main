package com.ayang.website.user.service.impl;

import com.ayang.website.common.annotaion.RedissonLock;
import com.ayang.website.common.event.UserRegisterEvent;
import com.ayang.website.common.utils.AssertUtil;
import com.ayang.website.user.dao.ItemConfigDao;
import com.ayang.website.user.dao.UserBackpackDao;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.ItemConfig;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.domain.entity.UserBackpack;
import com.ayang.website.user.domain.enums.ItemEnum;
import com.ayang.website.user.domain.enums.ItemTypeEnum;
import com.ayang.website.user.domain.vo.resp.BadgeResp;
import com.ayang.website.user.domain.vo.resp.UserInfoResp;
import com.ayang.website.user.service.UserService;
import com.ayang.website.user.service.adapter.UserAdapter;
import com.ayang.website.user.service.cache.ItemCache;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ItemCache itemCache;
    private final ItemConfigDao itemConfigDao;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long register(User insert) {
        userDao.save(insert);
        // 用户注册的事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, insert));
        return insert.getId();
    }

    @Override
    public UserInfoResp getUserInfo(Long uid) {
        User user = userDao.getById(uid);
        Integer modifyNameCount = userBackpackDao.getCountByValidItemId(uid, ItemEnum.MODIFY_NAME_CARD.getId());
        return UserAdapter.buildUserInfo(user, modifyNameCount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyName(Long uid, String name) {
        User oldUser = userDao.getByName(name);
        AssertUtil.isEmpty(oldUser, "名字重复,换个名字吧");
        UserBackpack modifyNameItem = userBackpackDao.getFirstValidItem(uid, ItemEnum.MODIFY_NAME_CARD.getId());
        AssertUtil.isNotEmpty(modifyNameItem, "改名卡数量不足，等后续活动获取改名卡吧");
        // 使用改名卡
        boolean success = userBackpackDao.useItem(modifyNameItem);
        if (success) {
            // 改名
            userDao.modifyName(uid, name);

        }
    }

    @Override
    public List<BadgeResp> badges(Long uid) {
        // 查询所有徽章
        List<ItemConfig> itemConfigs = itemCache.getByType(ItemTypeEnum.BADGE.getType());
        // 查询用户拥有的徽章
        List<UserBackpack> backpacks = userBackpackDao.getByItemId(uid, itemConfigs.stream().map(ItemConfig::getId).collect(Collectors.toList()));
        // 查询用户佩戴的徽章
        User user = userDao.getById(uid);
        return UserAdapter.buildBadgeResp(itemConfigs, backpacks, user);
    }

    @Override
    public void wearingBadge(Long uid, Long itemId) {
        // 确保有徽章
        UserBackpack firstValidItem = userBackpackDao.getFirstValidItem(uid, itemId);
        AssertUtil.isNotEmpty(firstValidItem, "您还没有该徽章，快去获得吧");
        // 确保这个物品是徽章
        ItemConfig itemConfig = itemConfigDao.getById(firstValidItem.getItemId());
        AssertUtil.equal(itemConfig.getType(), ItemTypeEnum.BADGE.getType(), "只有徽章才能佩戴");
        // 佩戴徽章
        userDao.wearingBadge(uid, itemId);
    }
}
