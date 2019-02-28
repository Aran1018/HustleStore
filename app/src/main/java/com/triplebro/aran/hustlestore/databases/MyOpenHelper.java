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
    private static final int DATABASE_VERSION = 2;//数据库版本号

    //用户表
    private static final String CREATE_TABLE_USERINFO = "" +
            "create table userInfo(" +
            "user_id VARCHAR(20) primary key, " +
            "user_name VARCHAR(20), " +
            "pass_word VARCHAR(20)," +
            "user_introduction VARCHAR(200)," +
            "user_Head VARCHAR(200)" +
            ")";
    private static final String CREATE_TABLE_GOODSINFO = "" +
            "create table goodsInfo(" +
            "user_id VARCHAR(20)," +
            "goods_id VARCHAR(50) primary key, " +
            "goods_name VARCHAR(200), " +
            "goods_count VARCHAR(200), " +
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
            "goods_id VARCHAR(50), " +
            "FOREIGN KEY (goods_id) REFERENCES goodsInfo(goods_id)" +
            ")";
    private static final String CREATE_TABLE_PUBLISHCONTENT = "" +
            "create table PublishContent(" +
            "publish_id integer primary key autoincrement, " +
            "user_id VARCHAR(200)," +
            "publish_content VARCHAR(300)," +
            "content_img VARCHAR(50)," +
            "publish_time VARCHAR(200)," +
            "great_number integer," +
            "FOREIGN KEY (user_id) REFERENCES userInfo(user_id)" +
            ")";
    private static final String CREATE_TABLE_COMMENTS = "" +
            "create table Comments(" +
            "comments_id integer primary key autoincrement, " +
            "publish_id integer," +
            "user_id VARCHAR(300)," +
            "comments_content VARCHAR(50)," +
            "comments_date VARCHAR(200),"+
            "FOREIGN KEY (user_id) REFERENCES userInfo(user_id)," +
            "FOREIGN KEY (publish_id) REFERENCES PublishContent(publish_id)" +
            ")";
    private static final String CREATE_TABLE_ADMINISTRATOR = "" +
            "create table admin(" +
            "admin_id varchar primary key," +
            "admin_password varchar(20)" +
            ")";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_GOODSINFO);
        db.execSQL(CREATE_TABLE_USERINFO);
        db.execSQL(CREATE_TABLE_GOODSIMG);
        db.execSQL(CREATE_TABLE_PUBLISHCONTENT);
        db.execSQL(CREATE_TABLE_COMMENTS);
        db.execSQL(CREATE_TABLE_ADMINISTRATOR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints 开启外键约束
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
