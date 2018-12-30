package com.triplebro.aran.hustlestore.beans;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/28.
 */


public class GoodsImg {
    int goodsImgId;
    String goodsId;
    String goodsImg_mainpath;
    String goodsImg_sec1path;
    String goodsImg_sec2path;
    String goodsImg_sec3path;
    String goodsImg_sec4path;


    public GoodsImg(int goodsImgId, String goodsId, String goodsImg_mainpath, String goodsImg_sec1path, String goodsImg_sec2path, String goodsImg_sec3path, String goodsImg_sec4path) {

        this.goodsImgId = goodsImgId;
        this.goodsId = goodsId;
        this.goodsImg_mainpath = goodsImg_mainpath;
        this.goodsImg_sec1path = goodsImg_sec1path;
        this.goodsImg_sec2path = goodsImg_sec2path;
        this.goodsImg_sec3path = goodsImg_sec3path;
        this.goodsImg_sec4path = goodsImg_sec4path;
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

    public GoodsImg() {
    }

    public int getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(int goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg_mainpath() {
        return goodsImg_mainpath;
    }

    public void setGoodsImg_mainpath(String goodsImg_mainpath) {
        this.goodsImg_mainpath = goodsImg_mainpath;
    }
}
