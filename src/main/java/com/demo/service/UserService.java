package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;

/**
 * 用户登录注册服务类
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 确定表名
     * @param tablename
     */
    public void setTablename(String tablename){
        userDao.setTablename(tablename);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 返回User类对象
     */
    public User login(String username,String password){
        return userDao.selectByUsernameAndPassword(username,password);
    }

    /**
     * 用户注册
     * @param user
     * @return 用户名已存在则返回false，注册成功则返回true
     */
    public boolean register(User user){
        if(userDao.selectByUsername(user.getUser_name())==null){
            userDao.insert(user);
            return true;
        }
        else {
            return false;
        }
    }
}
