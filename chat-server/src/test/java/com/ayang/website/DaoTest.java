package com.ayang.website;

import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author shy
 * @date 2023/12/5
 * @description DaoTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        List<User> list = userDao.list();
    }
}
