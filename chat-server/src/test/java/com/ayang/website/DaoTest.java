package com.ayang.website;


import com.ayang.website.common.utils.JwtUtils;
import com.ayang.website.user.domain.enums.IdempotentEnum;
import com.ayang.website.user.domain.enums.ItemEnum;
import com.ayang.website.user.service.LoginService;
import com.ayang.website.user.service.UserBackpackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author shy
 * @date 2023/12/5
 * @description DaoTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    public static final long UID = 2001L;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LoginService loginService;

    @Test
    public void jwt(){
        System.out.println(jwtUtils.createToken(1L));
        System.out.println(jwtUtils.createToken(1L));
        System.out.println(jwtUtils.createToken(1L));
    }


    @Test
    public void redis(){
        String token = loginService.login(UID);
        System.out.printf(token);
    }

    @Autowired
    private UserBackpackService userBackpackService;
    @Test
    public void acquireItem(){
        userBackpackService.acquireItem(UID, ItemEnum.REG_TOP10_BADGE.getId(), IdempotentEnum.UID, UID +"");
    }

    @Test
    public void getRandomNum(){

        for (int j = 0; j < 12; j++) {
            Random random = new Random();
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                res.append(random.nextInt(10));
            }
            System.out.println(res);
        }
    }
}
