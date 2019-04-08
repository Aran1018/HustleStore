package com.triplebro.aran.hustlestore.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;

import java.util.ArrayList;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/1.
 */


public class PublishContentManager {
    Context context;

    public PublishContentManager(Context context) {
        this.context = context;
    }

    public ArrayList<TrafficData> getTrendsTrafficData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String user_id = sharedPreferences.getString("user_id", "");

        ArrayList<TrafficData> list = new ArrayList<TrafficData>();
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor publishContent = db.query("PublishContent", new String[]{"content_img","publish_id","user_id"}, "user_id = ?", new String[]{user_id}, null, null, null);
        if (publishContent!=null&&publishContent.getCount()>0)
        {
            while (publishContent.moveToNext()){
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
}
