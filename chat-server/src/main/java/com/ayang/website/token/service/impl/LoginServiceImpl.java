package com.ayang.website.token.service.impl;

import com.ayang.website.token.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author shy
 * @date 2023/12/12
 * @description LoginServiceImpl
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(Long uid) {
        // todo 生成token
        return null;
    }
}
