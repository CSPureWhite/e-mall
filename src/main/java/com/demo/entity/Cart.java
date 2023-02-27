package com.demo.entity;

/**
 * 购物车数据表实体类
 */
public class Cart {
    private int cart_id;
    private int customer_id;
    private int goods_id;
    private int amount;

    public Cart(){}
    public int getCart_id() {
        return cart_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public int getAmount() {
        return amount;
    }
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
