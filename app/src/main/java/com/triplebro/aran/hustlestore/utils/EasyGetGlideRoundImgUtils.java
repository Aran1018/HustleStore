package com.triplebro.aran.hustlestore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/27.
 */


public class EasyGetGlideRoundImgUtils {
    public static void readGlideRoundImg(Context context, ImageView imageView){
        SharedPreferences userLogin = context.getSharedPreferences("userInfo", MODE_PRIVATE);
        String userId = userLogin.getString("user_id", "");
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor userInfo = writableDatabase.query("userInfo", new String[]{"user_Head"}, "user_id = ?", new String[]{userId}, null, null, null);
        if (userInfo != null && userInfo.getCount() > 0){
            while (userInfo.moveToNext()){
                Glide.with(context).load(userInfo.getString(0)).placeholder(R.drawable.bg_userhead).bitmapTransform(new GlideCircleTransform(context)).into(imageView);

            }
        }

    }
}
