package com.demo.entity;

/**
 * 商品实体类
 */
public class Goods {
    private int goods_id;
    private String goods_name;
    private String description;
    private double price;
    private int stock;
    private int sales_volume; //该变量为销量，不在数据表中

    public Goods(){}

    public int getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }
    public String getDescription(){return description;}

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    public int getSales_volume(){
        return sales_volume;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setSales_volume(int sales_volume){
        this.sales_volume=sales_volume;
    }
}
