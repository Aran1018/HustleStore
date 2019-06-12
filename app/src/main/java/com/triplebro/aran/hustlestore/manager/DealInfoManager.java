package com.triplebro.aran.hustlestore.manager;

import android.content.ContentValues;
import android.content.Context;

import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/11.
 */


public class DealInfoManager {
    Context context;

    public DealInfoManager(Context context) {
        this.context = context;
    }

    public boolean setbeanToCVaddDeal(String seller_id,String buyer_id,String goods_id,String order_date,String goods_name,String goodsImg_mainpath,String goods_price){
        DatabaseProvider databaseProvider = new DatabaseProvider(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("seller_id",seller_id);
        contentValues.put("buyer_id",buyer_id);
        contentValues.put("goods_id",goods_id);
        contentValues.put("order_date",order_date);
        contentValues.put("goods_name",goods_name);
        contentValues.put("goodsImg_mainpath",goodsImg_mainpath);
        contentValues.put("goods_price",goods_price);
        boolean result = databaseProvider.addDeal(contentValues,goods_id);

        return result;
    }
}
