package com.tz.user.service;

import com.tz.entity.User;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:26
 */
public interface UserService {
    /**
     * 登陆
     */
    User login(String username, String password);
}
