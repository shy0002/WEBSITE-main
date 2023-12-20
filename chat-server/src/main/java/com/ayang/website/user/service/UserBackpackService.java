package com.ayang.website.user.service;

import com.ayang.website.user.domain.enums.IdempotentEnum;

/**
 * <p>
 * 用户背包表 服务类
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-19
 */
public interface UserBackpackService {

    /**
     * 给用户发放一个物品
     *
     * @param uid            用户id
     * @param itemId         物品id
     * @param idempotentEnum 幂等类型
     * @param businessId     幂等唯一表示
     */
    void acquireItem(Long uid, Long itemId, IdempotentEnum idempotentEnum, String businessId);

}
