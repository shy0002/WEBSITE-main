package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.Black;
import com.ayang.website.user.mapper.BlackMapper;
import com.ayang.website.user.service.IBlackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 黑名单 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-26
 */
@Repository
public class BlackDao extends ServiceImpl<BlackMapper, Black>{

}
