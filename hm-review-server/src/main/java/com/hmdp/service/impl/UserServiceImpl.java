package com.hmdp.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;
import static com.hmdp.utils.SystemConstants.VERIFICATION_CODE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author <a href="https://github.com/shy0002“>ayang</a>
 * @since 2024-01-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Result sendCode(String phone, HttpSession session) {
        // 1、校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        }
        // 3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 4.保存验证码到session
        session.setAttribute(VERIFICATION_CODE, code);
        // 5.返回验证码,使用日志方式在后台输出，暂不调用第三方接口实现短信发送
        log.debug("发送短信验证码成功，验证码：{}", code);
        // 返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 1、校验手机号
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        }
        // 2、校验验证码
        Object cacheCode = session.getAttribute(VERIFICATION_CODE);
        String code = loginForm.getCode();
        if (Objects.isNull(cacheCode) || !Objects.equals(cacheCode.toString(), code)) {
            // 3、不一致，报错返回
            return Result.fail("验证码错误");
        }
        // 4、一致，根据手机号查询用户 select * form tb_user where phone = ?
        User user = lambdaQuery().eq(User::getPhone, phone).one();
        // 5、判断用户是否存在
        if (user == null) {
            // 6、不存在，创建新用户并保存
            user = createUserWithPhone(phone);
        }
        // 7、保存用户信息到session
        session.setAttribute("user", user.toDTO());
        return Result.ok();
    }

    private User createUserWithPhone(String phone) {
        // 1、创建用户
        User user = new User();
        user.setPhone(phone);
        String nickName = USER_NICK_NAME_PREFIX + RandomUtil.randomString(10);
        user.setNickName(nickName);
        // 2、保存用户
        save(user);
        return user;
    }
}
