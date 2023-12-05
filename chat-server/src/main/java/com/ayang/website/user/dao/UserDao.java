package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.mapper.UserMapper;
import com.ayang.website.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shy
 * @since 2023-12-05
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

}
