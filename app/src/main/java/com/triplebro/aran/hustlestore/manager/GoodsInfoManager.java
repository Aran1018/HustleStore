package com.triplebro.aran.hustlestore.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.GoodsImg;
import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.beans.UserInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/26.
 */


public class GoodsInfoManager {

    Context context;
    private String userId;
    private String anInt;
    private Goods goods;
    private GoodsImg goodsImg;

    public GoodsInfoManager(Context context) {
        this.context = context;

    }


    public List<Goods> getGoodsList() {
        List<Goods> goodsList = new ArrayList<>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();

        Cursor goodsInfoCursor = readableDatabase.query("goodsInfo",
                new String[]{"user_id",
                        "goods_id",
                        "goods_name",
                        "goods_price",
                        "goods_label",
                        "user_Head",
                        "goods_beLike"}, null, null,
                null, null, "goods_id desc", null);
        if (goodsInfoCursor != null && goodsInfoCursor.getCount() > 0) {
            while (goodsInfoCursor.moveToNext()) {
                Goods goods = new Goods();
                goods.setUser_id(goodsInfoCursor.getInt(0));
                goods.setGoods_id(goodsInfoCursor.getString(1));
                goods.setGoods_name(goodsInfoCursor.getString(2));
                goods.setGoods_price(goodsInfoCursor.getString(3));
                goods.setGoods_label(goodsInfoCursor.getString(4));
                goods.setUser_Head(goodsInfoCursor.getString(5));
                goods.setGoods_beLike(goodsInfoCursor.getInt(6));
                goodsList.add(goods);
            }
            goodsInfoCursor.close();
            readableDatabase.close();
        }

        return goodsList;
    }

    public List<Goods> getGoodsImg(List<Goods> list) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        for (Goods goods : list) {
            Cursor goodsImgCursor = readableDatabase.query("goodsImg",
                    new String[]{"goodsImg_mainpath"}, "goods_id = ?", new String[]{goods.getGoods_id()},
                    null, null, null, null);
            if (goodsImgCursor != null && goodsImgCursor.getCount() > 0) {
                while (goodsImgCursor.moveToNext()) {

                    String string = goodsImgCursor.getString(0);
                    goods.setGoodsImg_mainpath(string);

                }
                goodsImgCursor.close();
            }

        }
        readableDatabase.close();

        return list;

    }

    public List<Goods> getUserName(List<Goods> list) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        for (Goods goods : list) {
            Cursor user_name = readableDatabase.query("userInfo",
                    new String[]{"user_name", "user_Head"}, "user_id = ?", new String[]{String.valueOf(goods.getUser_id())},
                    null, null, null, null);
            if (user_name != null && user_name.getCount() > 0) {
                while (user_name.moveToNext()) {
                    goods.setUser_name(user_name.getString(0));
                    goods.setUser_Head(user_name.getString(1));

                    user_name.close();
                }
            }

        }
        readableDatabase.close();

        return list;
    }

    //TODO:getGoodsTrafficData传入详情页Adaapter
    public ArrayList<TrafficData> getGoodsTrafficData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String user_id = sharedPreferences.getString("user_id", "");

        ArrayList<TrafficData> list = new ArrayList<TrafficData>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor publishContent = db.query("PublishContent", new String[]{"content_img", "publish_id", "user_id"}, "user_id = ?", new String[]{user_id}, null, null, null);
        if (publishContent != null && publishContent.getCount() > 0) {
            while (publishContent.moveToNext()) {
                TrafficData trafficdata = new TrafficData();
                trafficdata.setPath(publishContent.getString(0));
                trafficdata.setPublish_id(publishContent.getString(1));
                trafficdata.setUserId(publishContent.getString(2));
                list.add(trafficdata);
            }
            publishContent.close();
            db.close();
        }

        db.close();
        return list;
    }

    public GoodsImg queryGoodsImgDetails(String goods_id) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getWritableDatabase();
        goodsImg = new GoodsImg();
        Cursor goodsInfoCursor = readableDatabase.query("goodsImg",
                new String[]{"goodsImg_mainpath", "goodsImg_sec1path", "goodsImg_sec2path", "goodsImg_sec3path", "goodsImg_sec4path"}, "goods_id=?", new String[]{goods_id},
                null, null, null, null);
        if (goodsInfoCursor != null && goodsInfoCursor.getCount() > 0) {
            goodsInfoCursor.moveToNext();
            goodsImg.setGoodsImg_mainpath(goodsInfoCursor.getString(0));
            goodsImg.setGoodsImg_sec1path(goodsInfoCursor.getString(1));
            goodsImg.setGoodsImg_sec2path(goodsInfoCursor.getString(2));
            goodsImg.setGoodsImg_sec3path(goodsInfoCursor.getString(3));
            goodsImg.setGoodsImg_sec4path(goodsInfoCursor.getString(4));
            goodsInfoCursor.close();
            readableDatabase.close();
        }


        return goodsImg;

    }

    public Goods queryGoodsDetails(String goods_id) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getWritableDatabase();
        Cursor goodsInfoCursor = readableDatabase.query("goodsInfo",
                new String[]{"user_id",
                        "goods_id",
                        "goods_name",
                        "goods_price",
                        "goods_label",
                        "user_Head",
                        "goods_beLike"}, "goods_id=?", new String[]{goods_id},
                null, null, null, null);
        if (goodsInfoCursor != null && goodsInfoCursor.getCount() > 0) {
            goodsInfoCursor.moveToNext();
            goods = new Goods();
            goods.setUser_id(goodsInfoCursor.getInt(0));
            goods.setGoods_id(goodsInfoCursor.getString(1));
            goods.setGoods_name(goodsInfoCursor.getString(2));
            goods.setGoods_price(goodsInfoCursor.getString(3));
            goods.setGoods_label(goodsInfoCursor.getString(4));
            goods.setUser_Head(goodsInfoCursor.getString(5));
            goods.setGoods_beLike(goodsInfoCursor.getInt(6));
            goodsInfoCursor.close();
            readableDatabase.close();
        }


        return goods;
    }
}
