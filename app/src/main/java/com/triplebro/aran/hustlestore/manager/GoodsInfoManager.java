package com.triplebro.aran.hustlestore.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.GoodsImg;
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
                null, null, null, null);
        if (goodsInfoCursor!=null&&goodsInfoCursor.getCount()>0)
        {
            while (goodsInfoCursor.moveToNext()){
                Goods goods = new Goods();
                goods.setUser_id(goodsInfoCursor.getInt(0));
                goods.setGoods_id(goodsInfoCursor.getInt(1));
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

    public List<GoodsImg> getGoodsImg(List<Goods> list){
        List<GoodsImg> goodsImgList = new ArrayList<>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        for (Goods goods: list) {
            Cursor goodsImgCursor = readableDatabase.query("goodsImg",
                    new String[]{"goodsImg_mainpath"}, "goods_id = ?", new String[]{String.valueOf(goods.getUser_id())},
                    null, null, null, null);
            if (goodsImgCursor!=null && goodsImgCursor.getCount()>0){
                while (goodsImgCursor.moveToNext()){
                    GoodsImg goodsImg = new GoodsImg();
                    goodsImg.setGoodsImg_mainpath(goodsImgCursor.getString(0));
                    goodsImgList.add(goodsImg);

                }
            }

        }
        return goodsImgList;

    }
    public List<UserInfo> getUserName(List<Goods> list){
        List<UserInfo> userInfoList = new ArrayList<>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        for (Goods goods:list) {
            Cursor user_name = readableDatabase.query("userInfo",
                    new String[]{"user_name","user_Head"}, "user_id = ?", new String[]{String.valueOf(goods.getUser_id())},
                    null, null, null, null);
            if (user_name!=null && user_name.getCount()>0){
                while (user_name.moveToNext()){
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUser_name(user_name.getString(0));
                    userInfo.setUser_Head(user_name.getString(1));
                    userInfoList.add(userInfo);

                }
            }
        }

        return userInfoList;
    }
}
