package com.tz.user.dao.impl;

import com.tz.entity.User;
import com.tz.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {
    @Resource
    private UserDao userDao;

    @Test
    public void selectUser() throws Exception {
        User user = userDao.selectUser("张三", "123123");
        if (user != null) {
            System.out.println(user);
        }
    }
}