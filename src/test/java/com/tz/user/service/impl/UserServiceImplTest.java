package com.tz.user.service.impl;

import com.tz.entity.User;
import com.tz.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void login() throws Exception {
        User user = userService.login("张三", "123123");
        if (user != null) {
            System.out.println(user);
        }
    }
}