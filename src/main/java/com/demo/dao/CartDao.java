package com.demo.dao;

import com.demo.entity.Cart;
import com.demo.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDao {
    private final QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDs());

    /**
     * 查询用户购物车中的所有商品
     * @return
     */
    public List<Cart> selectAll(int customer_id){
        String sql ="select * from cart where customer_id=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Cart.class),customer_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过商品在购物车中的id查询商品
     * @param cart_id
     * @return
     */
    public Cart selectById(int cart_id){
        String sql ="select * from cart where cart_id=?";
        try{
            return queryRunner.query(sql, new BeanHandler<Cart>(Cart.class),cart_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Cart IsExist(int customer_id,int goods_id){
        String sql ="select * from cart where customer_id=? and goods_id=?";
        try{
            return queryRunner.query(sql, new BeanHandler<Cart>(Cart.class),customer_id,goods_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加新商品
     * @param customer_id
     * @param goods_id
     * @return
     */
    public int insert(int customer_id, int goods_id) {
        String sql = "insert into cart values(null,?,?,1)";
        try {
            return queryRunner.update(sql, customer_id, goods_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除商品
     * @param cart_id
     * @return
     */
    public int delete(int cart_id){
        String sql = "delete from cart where cart_id=?";
        try{
            return queryRunner.update(sql, cart_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新商品数量
     * @param cart_id
     * @param amount
     * @return
     */
    public int update(int cart_id,int amount){
        String sql="update cart set amount=? where cart_id=?";
        try {
            return queryRunner.update(sql,amount,cart_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
