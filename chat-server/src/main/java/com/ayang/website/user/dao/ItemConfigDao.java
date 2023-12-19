package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.ItemConfig;
import com.ayang.website.user.mapper.ItemConfigMapper;
import com.ayang.website.user.service.IItemConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能物品配置表 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-19
 */
@Service
public class ItemConfigDao extends ServiceImpl<ItemConfigMapper, ItemConfig>{

}
