package com.demo.service;

import com.demo.dao.CartDao;
import com.demo.dao.GoodsDao;
import com.demo.entity.Cart;
import com.demo.entity.CartInfo;
import com.demo.entity.Goods;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private CartDao cartDao = new CartDao();
    private GoodsDao goodsDao = new GoodsDao();

    /**
     * 搜索购物车中的所有商品及商品信息
     * @param customer_id
     * @return
     */
    public List<CartInfo> showAllItems(int customer_id){
        List<Cart> cartList = cartDao.selectAll(customer_id);
        List<CartInfo> cartInfoList = new ArrayList<>();
        for(Cart cart:cartList){
            CartInfo cartinfo = new CartInfo();
            Goods goods = new Goods();
            goods=goodsDao.selectByGoodsId(cart.getGoods_id());
            if(goods==null){ //如果购物车中的商品已被删除，则将该商品从购物车表中移除
                cartDao.delete(cart.getCart_id());
            }
            else {
                cartinfo.setCart_id(cart.getCart_id());
                cartinfo.setCustomer_id(customer_id);
                cartinfo.setGoods_id(cart.getGoods_id());
                cartinfo.setGoods_name(goods.getGoods_name());
                cartinfo.setAmount(cart.getAmount());
                cartinfo.setPrice(goods.getPrice());
                cartinfo.setTotal_price();
                cartInfoList.add(cartinfo);
            }
        }
        return cartInfoList;
    }

    /**
     * 添加商品至购物车
     * @param customer_id
     * @param goods_id
     * @return
     */
    public boolean add (int customer_id, int goods_id){
        int num=0;
        Cart cart=cartDao.IsExist(customer_id, goods_id);
        if(cart==null){ //商品不在购物车中
            num = cartDao.insert(customer_id,goods_id);
        }
        else{
            num=cartDao.update(cart.getCart_id(),cart.getAmount()+1);//商品已在购物车中
        }
        if(num>0){
            return true;
        }
        else
            return false;
    }

    /**
     * 从购物车中删除商品或减少数量
     * @param cart_id
     * @return
     */
    public boolean delete(int cart_id){
        int num=0;
        int amount=cartDao.selectById(cart_id).getAmount();
        if(amount>1){
            num=cartDao.update(cart_id,amount-1);
        }
        else{
            num=cartDao.delete(cart_id);
        }
        if(num>0){
            return true;
        }
        else
            return false;
    }

    /**
     * 计算购物车中所有商品的合计金额
     * @param cartInfoList
     * @return
     */
    public double TotalAmount(List<CartInfo> cartInfoList){
        double result=0;
        for(CartInfo cartInfo:cartInfoList){
            result =result+cartInfo.getTotal_price();
        }
        return result;
    }
}
