package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/11.
 */


public class Goods {



    int user_id;
    String goods_id;
    String goods_name;
    String goods_price;
    String goods_label;
    String user_Head;
    int goods_beLike;
    String goodsImg_mainpath;
    String goodsImg_sec1path;
    String goodsImg_sec2path;
    String goodsImg_sec3path;
    String goodsImg_sec4path;


    public Goods() {
    }

    public Goods(int user_id, String goods_id, String goods_name, String goods_price, String goods_label, String user_Head, int goods_beLike, String goodsImg_mainpath, String goodsImg_sec1path, String goodsImg_sec2path, String goodsImg_sec3path, String goodsImg_sec4path) {
        this.user_id = user_id;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_label = goods_label;
        this.user_Head = user_Head;
        this.goods_beLike = goods_beLike;
        this.goodsImg_mainpath = goodsImg_mainpath;
        this.goodsImg_sec1path = goodsImg_sec1path;
        this.goodsImg_sec2path = goodsImg_sec2path;
        this.goodsImg_sec3path = goodsImg_sec3path;
        this.goodsImg_sec4path = goodsImg_sec4path;
    }

    public String getGoodsImg_mainpath() {
        return goodsImg_mainpath;
    }

    public void setGoodsImg_mainpath(String goodsImg_mainpath) {
        this.goodsImg_mainpath = goodsImg_mainpath;
    }

    public String getGoodsImg_sec1path() {
        return goodsImg_sec1path;
    }

    public void setGoodsImg_sec1path(String goodsImg_sec1path) {
        this.goodsImg_sec1path = goodsImg_sec1path;
    }

    public String getGoodsImg_sec2path() {
        return goodsImg_sec2path;
    }

    public void setGoodsImg_sec2path(String goodsImg_sec2path) {
        this.goodsImg_sec2path = goodsImg_sec2path;
    }

    public String getGoodsImg_sec3path() {
        return goodsImg_sec3path;
    }

    public void setGoodsImg_sec3path(String goodsImg_sec3path) {
        this.goodsImg_sec3path = goodsImg_sec3path;
    }

    public String getGoodsImg_sec4path() {
        return goodsImg_sec4path;
    }

    public void setGoodsImg_sec4path(String goodsImg_sec4path) {
        this.goodsImg_sec4path = goodsImg_sec4path;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_label() {
        return goods_label;
    }

    public void setGoods_label(String goods_label) {
        this.goods_label = goods_label;
    }

    public String getUser_Head() {
        return user_Head;
    }

    public void setUser_Head(String user_Head) {
        this.user_Head = user_Head;
    }

    public int getGoods_beLike() {
        return goods_beLike;
    }

    public void setGoods_beLike(int goods_beLike) {
        this.goods_beLike = goods_beLike;
    }
}
