package com.triplebro.aran.hustlestore.manager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;
import com.triplebro.aran.hustlestore.beans.CommentInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
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


public class CommentsManager {
    Context context;

    public CommentsManager(Context context) {
        this.context = context;
    }

    public void addCommentd(String publish_id, String comments_content) {
        DatabaseProvider databaseProvider = new DatabaseProvider(context);
        ContentValues contentValues = new ContentValues();

        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String comments_date = formatter.format(curDate);
        contentValues.put("publish_id", publish_id);
        contentValues.put("user_id", user_id);
        contentValues.put("comments_content", comments_content);
        contentValues.put("comments_date", comments_date);
        databaseProvider.addComment(contentValues, (Activity) context);
    }

    public List<CommentInfo> queryCommentInfoList(String publish_id) {
        DatabaseProvider databaseProvider = new DatabaseProvider(context);
        List<CommentInfo> commentInfos = databaseProvider.getCommentUserHeadAndName(databaseProvider.queryCommentInfoList(publish_id));
        return  commentInfos;
    }

}
