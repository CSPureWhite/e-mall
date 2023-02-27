package com.demo.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 数据库连接池工具类
 */
public class JDBCUtil {
    //定义数据库连接池变量
    private static DataSource ds;

    static {
        try {
            //加载配置文件
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 返回数据库连接池中的一个连接
     * @throws Exception
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放资源
     * @param conn
     */
    public static void close(Connection conn){
        try{
            if(conn!=null) {
                conn.close(); //归还连接
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池
     * @return 返回连接池
     */
    public static DataSource getDs() {
        return ds;
    }
}