package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/11.
 */


public class DealInfo {

    int order_id;

    String seller_id;

    String buyer_id;
    String goods_id;
    String order_date;
    String goods_name;
    String goodsImg_mainpath;
    String goods_price;
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoodsImg_mainpath() {
        return goodsImg_mainpath;
    }

    public void setGoodsImg_mainpath(String goodsImg_mainpath) {
        this.goodsImg_mainpath = goodsImg_mainpath;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public DealInfo(String seller_id, String buyer_id, String goods_id, String order_date) {
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.goods_id = goods_id;
        this.order_date = order_date;
    }

    public DealInfo() {
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
