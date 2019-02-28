package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.beans.UserInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.properties.Properties;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

public class PhotoDetailActivity extends BaseActivity implements View.OnClickListener {

    private String int_path;
    private String publish_id;
    private ImageView rvimg_list_userIcon;
    private TextView tv_userName;
    private TextView tv_user_introduction;
    private ImageView iv_content_img;
    private RelativeLayout rl_bt_back;
    private int mDistanceY;

    private Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int_path = intent.getStringExtra("int_path");
        publish_id = intent.getStringExtra("user_id");
        setContentView(R.layout.activity_photo_detail);
        initView();
    }

    private void queryDetailInfo() {

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        Cursor userInfo = readableDatabase.query("userInfo", new String[]{"user_name", "user_Head", "user_introduction"}, "user_id=?", new String[]{publish_id}, null, null, null);
        if (userInfo != null && userInfo.getCount() > 0) {
            while (userInfo.moveToNext()) {
                tv_userName.setText(userInfo.getString(0));
                Glide.with(this).load(userInfo.getString(1)).bitmapTransform(new GlideCircleTransform(this)).into(rvimg_list_userIcon);
                tv_user_introduction.setText(userInfo.getString(2));
            }
        }

    }

    private void initView() {
        rvimg_list_userIcon = findViewById(R.id.rvimg_list_userIcon);
        tv_userName = findViewById(R.id.tv_userName);
        tv_user_introduction = findViewById(R.id.tv_user_introduction);
        iv_content_img = findViewById(R.id.iv_content_img);
        rl_bt_back = findViewById(R.id.rl_bt_back);
        bt_back = findViewById(R.id.bt_back);
        rl_bt_back.setOnClickListener(this);
        bt_back.setOnClickListener(this);

    }

    private void initEvent() {

        Glide.with(this).load(int_path).bitmapTransform(new CropSquareTransformation(this)).into(iv_content_img);
        queryDetailInfo();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            sv_item.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_titlebar);
//                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,tv_find);
//                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_find_send);
//                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,bt_find_send);
//                }
//            });
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initEvent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_bt_back:
            case R.id.bt_back:
                finish();
                break;

        }
    }
}
