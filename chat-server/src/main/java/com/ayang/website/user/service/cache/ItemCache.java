package com.ayang.website.user.service.cache;

import com.ayang.website.user.dao.ItemConfigDao;
import com.ayang.website.user.domain.entity.ItemConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shy
 * @date 2023/12/20
 * @description ItemCache
 */
@Component
@RequiredArgsConstructor
public class ItemCache {

    private final ItemConfigDao itemConfigDao;

    @Cacheable(cacheNames = "item", key = "'itemsByType:'+#itemType")
    public List<ItemConfig> getByType(Integer itemType){
        return itemConfigDao.getByType(itemType);
    }

    @CacheEvict(cacheNames = "item", key = "'itemsByType:'+#itemType")
    public void evictByType(Integer itemType){
    }
}
