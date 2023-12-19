package com.ayang.website;


import com.ayang.website.common.utils.JwtUtils;
import com.ayang.website.common.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shy
 * @date 2023/12/5
 * @description DaoTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void jwt(){
        System.out.println(jwtUtils.createToken(1L));
        System.out.println(jwtUtils.createToken(1L));
        System.out.println(jwtUtils.createToken(1L));
    }


    @Test
    public void redis(){
        RedisUtils.set("name", "阿洋努力修炼");
        System.out.println(RedisUtils.get("name"));
    }
}
