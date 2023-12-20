package com.ayang.website.user.dao;

import com.ayang.website.common.domain.enums.YesOrNoEnum;
import com.ayang.website.user.domain.entity.UserBackpack;
import com.ayang.website.user.mapper.UserBackpackMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户背包表 数据库交互服务
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-19
 */
@Repository
public class UserBackpackDao extends ServiceImpl<UserBackpackMapper, UserBackpack> {

    public Integer getCountByValidItemId(Long uid, Long itemId) {
        return lambdaQuery()
                .eq(UserBackpack::getUid, uid)
                .eq(UserBackpack::getItemId, itemId)
                .eq(UserBackpack::getStatus, YesOrNoEnum.NO.getStatus())
                .count();
    }

    public UserBackpack getFirstValidItem(Long uid, Long itemId) {
        return lambdaQuery().eq(UserBackpack::getUid, uid)
                .eq(UserBackpack::getItemId, itemId)
                .eq(UserBackpack::getStatus, YesOrNoEnum.NO.getStatus())
                .orderByAsc(UserBackpack::getId)
                .last("limit 1")
                .one();

    }

    public boolean useItem(UserBackpack item) {
        return lambdaUpdate()
                .eq(UserBackpack::getItemId, item.getId())
                .eq(UserBackpack::getStatus, YesOrNoEnum.NO.getStatus())
                .set(UserBackpack::getStatus, YesOrNoEnum.YES.getStatus())
                .update();
    }

    public List<UserBackpack> getByItemId(Long uid, List<Long> itemIds) {
        return lambdaQuery()
                .eq(UserBackpack::getUid, uid)
                .eq(UserBackpack::getStatus, YesOrNoEnum.NO.getStatus())
                .in(UserBackpack::getItemId, itemIds)
                .list();
    }

    public UserBackpack getByIdempotent(String idempotent) {
        return lambdaQuery()
                .eq(UserBackpack::getIdempotent, idempotent)
                .one();
    }
}
