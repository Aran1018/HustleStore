package com.triplebro.aran.hustlestore.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/29.
 */


public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "YiAPP.db";//数据库名字
    private static final int DATABASE_VERSION = 1;//数据库版本号

    //用户表
    private static final String CREATE_TABLE_USERINFO = "" +
            "create table userInfo(" +
            "id integer primary key autoincrement," +
            "user_id VARCHAR(20), " +
            "user_name VARCHAR(20), " +
            "pass_word VARCHAR(20)," +
            "user_introduction VARCHAR(200)," +
            "user_Head VARCHAR(200)" +
            ")";
    private static final String CREATE_TABLE_GOODSINFO = "" +
            "create table goodsInfo(" +
            "id integer primary key autoincrement," +
            "user_id VARCHAR(20)," +
            "goods_id VARCHAR(20), " +
            "goods_name VARCHAR(200), " +
            "goods_price VARCHAR(20)," +
            "goods_label VARCHAR(20),"  +
            "user_Head VARCHAR(200)," +
            "goods_beLike number," +
            "FOREIGN KEY (user_id) REFERENCES userInfo(user_id)" +
            ")";
    private static final String CREATE_TABLE_GOODSIMG = "" +
            "create table goodsImg(" +
            "goodsImg_id integer primary key autoincrement, " +
            "goodsImg_mainpath VARCHAR(200)," +
            "goodsImg_sec1path VARCHAR(200)," +
            "goodsImg_sec2path VARCHAR(200)," +
            "goodsImg_sec3path VARCHAR(200)," +
            "goodsImg_sec4path VARCHAR(200)," +
            "goods_id VARCHAR(20), " +
            "FOREIGN KEY (goods_id) REFERENCES goodsInfo(goods_id)" +
            ")";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_GOODSINFO);
        db.execSQL(CREATE_TABLE_USERINFO);
        db.execSQL(CREATE_TABLE_GOODSIMG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
