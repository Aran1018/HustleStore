package com.triplebro.aran.hustlestore.activites;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.properties.Properties;
import com.triplebro.aran.hustlestore.utils.ChooseImageDialogUtil;
import com.triplebro.aran.hustlestore.utils.PermissionUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

import static com.triplebro.aran.hustlestore.properties.Properties.REQUEST_CODE_CHOOSE;
import static com.triplebro.aran.hustlestore.properties.Properties.SEND_CONTEXT;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/26.
 */


public class SendNewsActivity extends BaseActivity {


    private ImageView iv_willsend;
    private Button bt_send_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendthings);
        bt_send_new = findViewById(R.id.bt_send_new);
        iv_willsend = findViewById(R.id.iv_willsend);
        iv_willsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImageDialogUtil.showSelectSubmitDialog(SendNewsActivity.this, Properties.SEND_CONTEXT);
            }
        });
        bt_send_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper myOpenHelper = new MyOpenHelper(SendNewsActivity.this);
                SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                SharedPreferences sendContext = getSharedPreferences("sendContext", MODE_PRIVATE);
                SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                String userId = userInfo.getString("user_id", "");
                String send_img = sendContext.getString("SEND_IMG", "");

                contentValues.put("content_img",send_img);
                contentValues.put("user_id",userId);
                contentValues.put("publish_time", formatter.format(curDate));
                long publishContent = writableDatabase.insert("PublishContent", null, contentValues);
                if (publishContent>0){
                    finish();
                    Toast.makeText(SendNewsActivity.this, "小事发布成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Properties.SEND_CONTEXT:
                SharedPreferences sharedPreferences = this.getSharedPreferences("sendContext",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    Glide.with(this).load(data.getData()).bitmapTransform(new CropSquareTransformation(this)).into(iv_willsend);
                }
                edit.putString("SEND_IMG", String.valueOf(data.getData()));
                edit.apply();
                break;
        }


    }
}
