package com.tz.user.dao;

import com.tz.entity.User;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:21
 */
public interface UserDao {
    /**
     * 根据用户名和密码查询指定用户
     */
    User selectUser(String username, String password);
}
