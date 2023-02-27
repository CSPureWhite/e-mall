package com.demo.dao;

import com.demo.entity.User;
import com.demo.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 顾客和管理员的Dao类
 */
public class UserDao{
    private String tablename;  //需要确定操作的是customer表还是admin表
    private final QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDs());
    public void setTablename(String tablename){
        this.tablename=tablename;
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return 返回User对象
     */
    public User selectByUsername(String username) {
        String sql = "select * from " +tablename+" where user_name=?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return 返回User对象
     */
    public User selectByUsernameAndPassword(String username, String password) {
        String sql = "select * from " +tablename+" where user_name=? and password=?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class),username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    public void insert( User user) {
        String sql = "insert into "+tablename+" values(null,?,?,?)";
        try{
            queryRunner.update(sql,user.getUser_name(),user.getPassword(),user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
