package com.demo.dao;

import com.demo.entity.Goods;
import com.demo.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDao {
    private final QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDs());

    /**
     * 根据商品名称进行模糊查询
     * @param goodsname
     * @return Goods对象链表
     */
    public List<Goods> selectByGoodsName(String goodsname) {
        String sql = "select * from goods where goods_name like ?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class),'%'+goodsname+'%');
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据商品id查询商品
     * @param id
     * @return Goods对象
     */
    public Goods selectByGoodsId(int id){
        String sql ="select * from goods where goods_id=?";
        try{
            return queryRunner.query(sql,new BeanHandler<Goods>(Goods.class), id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有商品
     * @return
     */
    public List<Goods> selectAll(){
        String sql ="select * from goods";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Goods.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    public int update(Goods goods){
        String sql ="update goods set goods_name=?, description=?, price=?, stock=? where goods_id=? ";
        try{
            return queryRunner.update(sql,goods.getGoods_name(),goods.getDescription(),goods.getPrice(),goods.getStock(),goods.getGoods_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 添加新商品
     * @param goods
     * @return
     */
    public int insert(Goods goods){
        String sql ="insert into goods values(null,?,?,?,?)";
        try{
            return queryRunner.update(sql,goods.getGoods_name(),goods.getDescription(),goods.getPrice(),goods.getStock());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    public int delete(int id){
        String sql ="delete from goods where goods_id=?";
        try {
            return queryRunner.update(sql,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
