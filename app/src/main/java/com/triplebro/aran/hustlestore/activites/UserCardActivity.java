package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.MyListAdapter;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.beans.UserInfo;
import com.triplebro.aran.hustlestore.manager.ListManager;
import com.triplebro.aran.hustlestore.utils.ChooseImageDialogUtil;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/12.
 */


public class UserCardActivity extends Activity implements View.OnClickListener {


    private boolean getData;
    private RelativeLayout rl_back;
    private Button bt_back;
    private ImageView rv_userImg;
    private RecyclerView rcv_findingList;
    private SharedPreferences userLogin;
    private TextView tv_userdescribe;
    private TextView tv_username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard);
        initView();
        setOnclick();
        isGetData();
    }

    private void initView() {
        rl_back = findViewById(R.id.rl_back);
        tv_userdescribe = findViewById(R.id.tv_userdescribe);
        tv_username = findViewById(R.id.tv_username);
        bt_back = findViewById(R.id.bt_back);
        rv_userImg = findViewById(R.id.rv_userImg);
        rcv_findingList = findViewById(R.id.rcv_findingList);
    }

    public void isGetData() {
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        List<ContextInfo> contextInfoList = new ListManager(this).getContextInfoList(user_id);
        List<ContextInfo> fullContextInfoList = new ListManager(this).getFullContextInfoList(contextInfoList);
        MyListAdapter myListAdapter = new MyListAdapter(this, fullContextInfoList);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_findingList.setLayoutManager(manager);
        rcv_findingList.setAdapter(myListAdapter);
        DatabaseProvider databaseProvider = new DatabaseProvider(this);
        UserInfo userInfo = databaseProvider.getsingleUserBean(user_id);

        tv_username.setText(userInfo.getUser_name());
        tv_userdescribe.setText(userInfo.getUser_introduction());
        Glide.with(this).load(userInfo.getUser_Head()).bitmapTransform(new GlideCircleTransform(this)).into(rv_userImg);

    }
    private void setOnclick() {
        rl_back.setOnClickListener(this);
        bt_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_back:
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
