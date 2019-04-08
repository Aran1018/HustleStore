package com.triplebro.aran.hustlestore.activites;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.MyListAdapter;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.manager.ListManager;
import com.triplebro.aran.hustlestore.properties.Properties;
import com.triplebro.aran.hustlestore.utils.ChooseImageDialogUtil;
import com.triplebro.aran.hustlestore.utils.EasyGetGlideRoundImgUtils;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.io.File;
import java.util.List;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/11/1.
 */


public class AllcardActivity extends BaseActivity implements View.OnClickListener{


    private RelativeLayout rl_back;
    private Button bt_back;
    private ImageView rv_userImg;
    private RecyclerView rcv_findingList;
    private SharedPreferences userLogin;
    private String userHead;
    private long timeStamp;
    private File userHeadFile;
    private TextView tv_userdescribe;
    private TextView tv_username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard);
        rl_back = findViewById(R.id.rl_back);
        tv_userdescribe = findViewById(R.id.tv_userdescribe);
        tv_username = findViewById(R.id.tv_username);
        bt_back = findViewById(R.id.bt_back);
        rv_userImg = findViewById(R.id.rv_userImg);
        rcv_findingList = findViewById(R.id.rcv_findingList);
        setOnclick();
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("user_name");
        String user_introduction = intent.getStringExtra("user_introduction");
        tv_username.setText(user_name);
        tv_userdescribe.setText(user_introduction);
        EasyGetGlideRoundImgUtils.readGlideRoundImg(this,rv_userImg);
        isGetData();
    }




    private void setOnclick() {
        rl_back.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        rv_userImg.setOnClickListener(this);

    }
    private void isGetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");
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
    }
    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.bt_back:
            case R.id.rl_back:
                finish();
                break;
//            case R.id.rv_userImg:
//
//                userLogin = getSharedPreferences("userInfo", MODE_PRIVATE);
//
//                userHead = userLogin.getString("user_Head", "");
//
//                timeStamp = System.currentTimeMillis();
//                ChooseImageDialogUtil.showSelectSubmitDialog(this);
//                break;

        }
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        boolean isCheck = true;
//        SharedPreferences.Editor edit = userLogin.edit();
//        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
//        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        switch (requestCode) {
//            case Properties.FROM_GALLERY:
//                if (resultCode == RESULT_OK) {
//                    userHeadFile = new File(String.valueOf(data.getData()));
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//
//                        Glide.with(this).load(data.getData()).bitmapTransform(new GlideCircleTransform(this)).into(rv_userImg);
//                    }
//                    edit.putString("user_Head", String.valueOf(data.getData()));
//                    contentValues.put("user_Head",String.valueOf(data.getData()));
//                    writableDatabase.update("userInfo",contentValues,"user_id = ?",new String[]{userLogin.getString("user_id","")});
//                    writableDatabase.close();
//                } else{
//                    isCheck = false;
//                }
//                break;
//        }
//        if(isCheck){
//            edit.commit();
//        }else{
//            Toast.makeText(this, "取消修改", Toast.LENGTH_SHORT).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
