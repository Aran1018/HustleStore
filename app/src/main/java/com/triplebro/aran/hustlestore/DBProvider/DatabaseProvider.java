package com.triplebro.aran.hustlestore.DBProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coder.zzq.smartshow.topbar.SmartTopbar;
import com.triplebro.aran.hustlestore.activites.AllPhotoDetailActivity;
import com.triplebro.aran.hustlestore.beans.CommentInfo;
import com.triplebro.aran.hustlestore.beans.DealInfo;
import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.UserInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/9.
 */


public class DatabaseProvider {
    Context context;

    public DatabaseProvider(Context context) {
        this.context = context;
    }

    public List<CommentInfo> queryCommentInfoList(String publish_id) {

        ArrayList<CommentInfo> commentInfos = new ArrayList<>();

        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();


        Cursor commentsCursor = db.query("Comments", new String[]{"comments_date", "comments_content", "user_id"}, "publish_id=?", new String[]{publish_id}, null, null, "comments_id desc");
        if (commentsCursor != null && commentsCursor.getCount() > 0) {
            while (commentsCursor.moveToNext()) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.setComments_date(commentsCursor.getString(0));
                commentInfo.setComments_content(commentsCursor.getString(1));
                commentInfo.setUser_id(commentsCursor.getString(2));
                commentInfos.add(commentInfo);
            }
            commentsCursor.close();
            db.close();
        }

        return commentInfos;
    }

    public List<CommentInfo> getCommentUserHeadAndName(List<CommentInfo> queryCommentInfoList) {

        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getReadableDatabase();
        for (CommentInfo commentInfo : queryCommentInfoList) {

            Cursor cursor = writableDatabase.query("userInfo", new String[]{"user_name", "user_Head"}, "user_id=?", new String[]{commentInfo.getUser_id()}, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    commentInfo.setUser_name(cursor.getString(0));
                    commentInfo.setUser_Head(cursor.getString(1));
                }
            }
        }
        return queryCommentInfoList;
    }

    public void addComment(ContentValues commentInfo, Activity activity) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        long comments = writableDatabase.insert("Comments", null, commentInfo);
        if (comments != -1) {
            SmartTopbar.setting().msgTextSizeSp(15);
            SmartTopbar.get(activity).show("评论成功");
        }
        writableDatabase.close();
    }

    public Goods getGoodsDetails(String goods_id) {
        Goods goods = new Goods();

        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("goodsInfo", new String[]{"goods_name", "goods_price"}, "goods_id=?", new String[]{goods_id}, null, null, null);
        Cursor goodsImgCursor = writableDatabase.query("goodsImg", new String[]{"goodsImg_mainpath"}, "goods_id=?", new String[]{goods_id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                goods.setGoods_name(cursor.getString(0));
                goods.setGoods_price(cursor.getString(1));
            }
            cursor.close();

        }

        if (goodsImgCursor != null && goodsImgCursor.getCount() > 0) {
            while (goodsImgCursor.moveToNext()) {
                goods.setGoodsImg_mainpath(goodsImgCursor.getString(0));
            }
            goodsImgCursor.close();
            writableDatabase.close();

        } else {
            goods.setGoods_name("");
            goods.setGoods_price("");
            goods.setGoodsImg_mainpath("");
        }
        return goods;
    }

    public UserInfo getDealUserNameAndHead(String user_id) {
        UserInfo userInfo = new UserInfo();

        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("userInfo", new String[]{"user_name", "user_Head"}, "user_id=?", new String[]{user_id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                userInfo.setUser_name(cursor.getString(0));
                userInfo.setUser_Head(cursor.getString(1));
            }
        } else {
            userInfo.setUser_name("");
            userInfo.setUser_Head("");
        }
        return userInfo;
    }

    public boolean addDeal(ContentValues contentValues, String goods_id) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        long usedOrder = writableDatabase.insert("UsedOrder", null, contentValues);
        writableDatabase.delete("goodsImg", "goods_id=?", new String[]{goods_id});
        writableDatabase.delete("goodsInfo", "goods_id=?", new String[]{goods_id});
        if (usedOrder != -1) {
            return true;
        }
        writableDatabase.close();
        return false;
    }

    public List<DealInfo> getMydealList() {

        ArrayList<DealInfo> list = new ArrayList<DealInfo>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor usedOrder = writableDatabase.query("UsedOrder", new String[]{"order_id", "order_date", "goods_name", "goodsImg_mainpath", "goods_price"}, null, null, null, null, "order_id desc");
        if (usedOrder != null && usedOrder.getCount() > 0) {
            while (usedOrder.moveToNext()) {
                DealInfo dealInfo = new DealInfo();
                dealInfo.setOrder_id(usedOrder.getInt(0));
                dealInfo.setOrder_date(usedOrder.getString(1));
                dealInfo.setGoods_name(usedOrder.getString(2));
                dealInfo.setGoodsImg_mainpath(usedOrder.getString(3));
                dealInfo.setGoods_price(usedOrder.getString(4));
                list.add(dealInfo);
            }
            usedOrder.close();
            writableDatabase.close();
        }
        return list;
    }

    public void deleteOrder(String goods_id) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();

        writableDatabase.delete("UsedOrder", "goods_id=?", new String[]{goods_id});
        writableDatabase.close();
    }

    public UserInfo getsingleUserBean(String user_id) {
        UserInfo userInfo = new UserInfo();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("userInfo", new String[]{"user_name", "user_introduction", "user_Head"}, "user_id=?", new String[]{user_id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                userInfo.setUser_name(cursor.getString(0));
                userInfo.setUser_introduction(cursor.getString(1));
                userInfo.setUser_Head(cursor.getString(2));
            }
            writableDatabase.close();
            cursor.close();
        }
        return userInfo;

    }

}
