package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.ItemConfig;
import com.ayang.website.user.mapper.ItemConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 功能物品配置表 数据库交互服务
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-19
 */
@Repository
public class ItemConfigDao extends ServiceImpl<ItemConfigMapper, ItemConfig> {

    public List<ItemConfig> getByType(Integer itemType) {
        return lambdaQuery()
                .eq(ItemConfig::getType, itemType)
                .list();
    }
}
