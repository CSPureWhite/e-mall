package com.demo.service;

import com.demo.dao.CartDao;
import com.demo.dao.GoodsDao;
import com.demo.dao.OrdersDao;
import com.demo.entity.CartInfo;
import com.demo.entity.Goods;
import com.demo.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersService {
    private OrdersDao ordersDao = new OrdersDao();
    private GoodsDao goodsDao = new GoodsDao();
    private CartDao cartDao =new CartDao();
    private CartService cartService= new CartService();

    /**
     * 用户下单
     * @param customer_id
     * @return 返回购物车中库存充足而成功下单的商品信息链表
     */
    public List<CartInfo> place_order(int customer_id){
        List<CartInfo> cartInfoList=cartService.showAllItems(customer_id);
        List<CartInfo> cartInfoList1=new ArrayList<>();
        for(CartInfo cartInfo:cartInfoList){
            Goods goods=goodsDao.selectByGoodsId(cartInfo.getGoods_id());
            int minus=goods.getStock()-cartInfo.getAmount();
            if(minus<0){  //商品购物车所选数量大于库存，无法下单
                continue;
            }
            cartInfoList1.add(cartInfo); //加入结果链表
            goods.setStock(minus); //更新库存
            goodsDao.update(goods);
            ordersDao.insert(cartInfo); //添加购买记录
            cartDao.delete(cartInfo.getCart_id()); //删除购物车中的商品记录
        }
        return cartInfoList1;
    }

    /**
     * 通过顾客id搜索购买记录
     * @param customer_id
     * @return
     */
    public List<Orders> searchByCustomerId(int customer_id){
        return ordersDao.selectByCustomerId(customer_id);
    }

    /**
     * 通过商品id搜索购买记录
     * @param goods_id
     * @return
     */
    public List<Orders> searchByGoodsId(int goods_id){
        return ordersDao.selectByGoodsId(goods_id);
    }

    /**
     * 展示全部用户信息
     * @return
     */
    public List<Orders> showAll(){
        return ordersDao.selectAll();
    }

    /**
     * 计算订单合计金额
     * @param ordersList
     * @return
     */
    public double TotalAmount(List<Orders> ordersList){
        double result=0;
        for(Orders orders:ordersList){
            result = result+orders.getTotal_price();
        }
        return result;
    }
}
