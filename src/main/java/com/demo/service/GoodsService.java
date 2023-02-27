package com.demo.service;

import com.demo.dao.GoodsDao;
import com.demo.dao.OrdersDao;
import com.demo.entity.Goods;
import com.demo.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class GoodsService {
    private GoodsDao goodsDao=new GoodsDao();

    /**
     * 搜索商品
     * @param goodsname
     * @return
     */
    public List<Goods> searchGoods(String goodsname){
        return goodsDao.selectByGoodsName(goodsname);
    }

    /**
     * 展示所有商品
     * @return
     */
    public List<Goods> showAllGoods(){
        return goodsDao.selectAll();
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    public boolean addGoods(Goods goods){
        if(goodsDao.insert(goods)>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    public boolean deleteGoods(int id){
        if(goodsDao.selectByGoodsId(id)!=null){
            if(goodsDao.delete(id)>0){
                return true;
            }
        }
        return false;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    public boolean updateGoods(Goods goods){
        if(goodsDao.selectByGoodsId(goods.getGoods_id())!=null){
            if(goodsDao.update(goods)>0){
                return true;
            }
        }
        return false;
    }

    /**
     * 通过商品id查找商品
     * @param goods_id
     * @return
     */
    public Goods searchById(int goods_id){
        return goodsDao.selectByGoodsId(goods_id);
    }

    /**
     * 查询商品销量
     * @param goods_id
     * @return
     */
    public List<Goods> getSales(int goods_id){
        List<Goods> goodsList = new ArrayList<>();
        if (goods_id == 0) { //查询所有商品的销量
            goodsList = goodsDao.selectAll();
        }
        else {              //查询对应id商品的销量
            Goods goods_temp=goodsDao.selectByGoodsId(goods_id); //确定是否存在该id对应的商品
            if(goods_temp!=null) {
                goodsList.add(goods_temp);
            }
        }
        OrdersDao ordersDao = new OrdersDao();
        //获得所选商品的销量
        if(!goodsList.isEmpty()) { //链表中有商品才进行统计
            for (Goods goods : goodsList) { //逐个商品统计
                int volume = 0;
                List<Orders> ordersList = ordersDao.selectByGoodsId(goods.getGoods_id());
                for (Orders orders : ordersList) {  //逐个订单统计下单数量
                    volume = volume + orders.getAmount();
                }
                goods.setSales_volume(volume);
            }
        }
        return goodsList;
    }
}
