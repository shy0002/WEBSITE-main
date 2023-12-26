package com.ayang.website.user.dao;

import com.ayang.website.user.domain.entity.UserRole;
import com.ayang.website.user.mapper.UserRoleMapper;
import com.ayang.website.user.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/shy0002">ayang</a>
 * @since 2023-12-26
 */
@Repository
public class UserRoleDao extends ServiceImpl<UserRoleMapper, UserRole>{

}
