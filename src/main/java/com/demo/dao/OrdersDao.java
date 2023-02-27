package com.demo.dao;

import com.demo.entity.CartInfo;
import com.demo.entity.Orders;
import com.demo.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class OrdersDao {
    private final QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDs());

    /**
     * 添加购物记录
     * @param cartinfo
     * @return
     */
    public int insert(CartInfo cartinfo){
        String sql = "insert into orders(customer_id, goods_id, goods_name, amount, price, total_price) values(?,?,?,?,?,?)";
        try{
            return queryRunner.update(sql,cartinfo.getCustomer_id(),cartinfo.getGoods_id(),cartinfo.getGoods_name(),cartinfo.getAmount(),cartinfo.getPrice(),cartinfo.getTotal_price());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println('a');
        return 0;
    }

    /**
     * 查询所有购买记录
     * @return
     */
    public List<Orders> selectAll(){
        String sql = "select* from orders";
        try{
            return queryRunner.query(sql, new BeanListHandler<>(Orders.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据顾客id查询购买记录
     * @param customer_id
     * @return
     */
    public List<Orders> selectByCustomerId(int customer_id){
        String sql="select * from orders where customer_id=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Orders.class),customer_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据商品id查询购买记录
     * @param goods_id
     * @return
     */
    public List<Orders> selectByGoodsId(int goods_id){
        String sql="select * from orders where goods_id=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Orders.class),goods_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
