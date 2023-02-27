package com.demo.entity;

/**
 * 顾客和管理员的实体类
 */
public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
    public User(){}
    public User(String user_name,String password,String email){
        this.user_name=user_name;
        this.password=password;
        this.email=email;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
