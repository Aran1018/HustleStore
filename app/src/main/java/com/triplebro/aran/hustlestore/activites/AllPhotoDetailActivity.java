package com.triplebro.aran.hustlestore.activites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.utils.GeneralUtils;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.glide.transformations.CropSquareTransformation;

public class AllPhotoDetailActivity extends BaseActivity implements View.OnClickListener {

    private String int_path;
    private String publish_id;
    private String user_id;
    private ImageView rvimg_list_userIcon;
    private TextView tv_userName;
    private TextView tv_user_introduction;
    private ImageView iv_content_img;
    private TextView tv_findings_date;
    private TextView tv_findings_time;
    private Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int_path = intent.getStringExtra("int_path");
        publish_id = intent.getStringExtra("publish_id");
        user_id = intent.getStringExtra("user_id");
        setContentView(R.layout.activity_photo_detail_all);
        initView();
    }

    private void queryDetailInfo() {

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        Cursor userInfo = readableDatabase.query("userInfo", new String[]{"user_name", "user_Head", "user_introduction"}, "user_id=?", new String[]{user_id}, null, null, null);
        if (userInfo != null && userInfo.getCount() > 0) {
            while (userInfo.moveToNext()) {
                tv_userName.setText(userInfo.getString(0));
                Glide.with(this).load(userInfo.getString(1)).bitmapTransform(new GlideCircleTransform(this)).into(rvimg_list_userIcon);
                tv_user_introduction.setText(userInfo.getString(2));
            }
        }
        Cursor publishContent = readableDatabase.query("PublishContent", new String[]{"publish_time"}, "publish_id=?", new String[]{publish_id}, null, null, null);
        if (publishContent != null && publishContent.getCount() > 0) {
            while (publishContent.moveToNext()) {
                tv_findings_date.setText(GeneralUtils.GetStringDate(publishContent.getString(0)));
                String[] time = publishContent.getString(0).split("   ");
                tv_findings_time.setText(time[1].substring(0, 5));
            }
        }
    }

    private void initView() {
        rvimg_list_userIcon = findViewById(R.id.rvimg_list_userIcon);
        tv_userName = findViewById(R.id.tv_userName);
        tv_user_introduction = findViewById(R.id.tv_user_introduction);
        tv_findings_date = findViewById(R.id.tv_findings_date);
        tv_findings_time = findViewById(R.id.tv_findings_time);
        iv_content_img = findViewById(R.id.iv_content_img);
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(this);
    }


    private void deleteTrends() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        readableDatabase.delete("PublishContent", "publish_id=?", new String[]{publish_id});
        readableDatabase.close();
    }

    private void initEvent() {
        Glide.with(this).load(int_path).bitmapTransform(new CropSquareTransformation(this)).into(iv_content_img);
        queryDetailInfo();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initEvent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                finish();
                break;

        }
    }
}
