package com.demo.entity;

import java.sql.Timestamp;

/**
 * 购买记录实体类
 */
public class Orders {
    private int order_id;
    private int customer_id;
    private int goods_id;
    private String goods_name;
    private int amount;
    private double price;
    private double total_price;
    private Timestamp time;

    public int getOrder_id() {
        return order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setTime(Timestamp time){this.time = time;}
}
