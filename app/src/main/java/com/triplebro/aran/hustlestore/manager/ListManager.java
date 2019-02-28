package com.triplebro.aran.hustlestore.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triplebro.aran.hustlestore.beans.ContextInfo;
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
 * Created by Aran on 2019/1/1.
 */


public class ListManager {

    Context context;

    public ListManager(Context context) {
        this.context = context;
    }

    public List<ContextInfo> getContextInfoList() {
        List<ContextInfo> contextInfos = new ArrayList<>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        Cursor publishContent = writableDatabase.query("PublishContent", new String[]{"content_img", "publish_content", "user_id"}, null, null, null, null, null);
        if (publishContent != null && publishContent.getCount() > 0) {

            while (publishContent.moveToNext()) {
                ContextInfo contextInfo = new ContextInfo();
                contextInfo.setContent_img(publishContent.getString(0));
                contextInfo.setPublish_content(publishContent.getString(1));
                String string = publishContent.getString(2);
                contextInfo.setUser_id(string);
                System.out.println("===============" + contextInfo.toString());
                contextInfos.add(contextInfo);

            }
        }
//        Cursor userInfo = writableDatabase.query("userInfo", new String[]{"user_name", "user_Head"}, "user_id=?", new String[]{string}, null, null, null);
//        if (userInfo!=null&&userInfo.getCount()>0){
//            while (userInfo.moveToNext()){
//                contextInfo.setUserName(userInfo.getString(0));
//                contextInfo.setUserHead(userInfo.getString(1));
//            }
//        }
        writableDatabase.close();
        return contextInfos;

    }

    public List<ContextInfo> getFullContextInfoList(List<ContextInfo> contextInfos) {

        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();

        for (ContextInfo contextInfo : contextInfos) {

            Cursor userInfo = writableDatabase.query("userInfo", new String[]{"user_name", "user_Head"}, "user_id=?", new String[]{contextInfo.getUser_id()}, null, null, null);
            if (userInfo != null && userInfo.getCount() > 0) {
                while (userInfo.moveToNext()) {
                    contextInfo.setUserName(userInfo.getString(0));
                    contextInfo.setUserHead(userInfo.getString(1));
                }
            }

        }

        return contextInfos;

    }


}
