package com.tz.user.service.impl;

import com.tz.entity.User;
import com.tz.user.dao.UserDao;
import com.tz.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.selectUser(username, password);
    }
}
