package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.properties.Properties;
import com.triplebro.aran.hustlestore.utils.ChooseImageDialogUtil;

import java.io.File;

import jp.wasabeef.glide.transformations.CropSquareTransformation;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/28.
 */


public class AddGoodsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_main;
    private ImageView iv_sec1;
    private ImageView iv_sec2;
    private ImageView iv_sec3;
    private ImageView iv_sec4;
    private String userId;
    private long timeStamp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences userInfo;
    private File userMainImage;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writableDatabase;
    private ContentValues goodsImg;
    private ContentValues goodsInfo;
    private SharedPreferences.Editor edit;
    private EditText ed_goodsId;
    private EditText ed_goodsPrice;
    private EditText ed_goodsLabel;
    int SetCount = 0;
    private Button bt_submit_insert;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoods);
        initView();
        initData();
    }

    private void returnImgUtils(String key, ImageView imageView, Intent data, SharedPreferences.Editor edit) {

        userMainImage = new File(String.valueOf(data.getData()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Glide.with(this).load(data.getData()).bitmapTransform(new CropSquareTransformation(this)).into(imageView);
        }
        edit.putString(key, String.valueOf(data.getData()));

    }

    private void initView() {
        iv_main = findViewById(R.id.iv_main);
        iv_sec1 = findViewById(R.id.iv_sec1);
        iv_sec2 = findViewById(R.id.iv_sec2);
        iv_sec3 = findViewById(R.id.iv_sec3);
        iv_sec4 = findViewById(R.id.iv_sec4);
        ed_goodsId = findViewById(R.id.ed_goodsId);
        ed_goodsPrice = findViewById(R.id.ed_goodsPrice);
        ed_goodsLabel = findViewById(R.id.ed_goodsLabel);
        bt_submit_insert = findViewById(R.id.bt_submit_insert);
        iv_main.setOnClickListener(this);
        iv_sec1.setOnClickListener(this);
        iv_sec2.setOnClickListener(this);
        iv_sec3.setOnClickListener(this);
        iv_sec4.setOnClickListener(this);
        bt_submit_insert.setOnClickListener(this);
    }

    private void initData() {
        myOpenHelper = new MyOpenHelper(this);
        writableDatabase = myOpenHelper.getWritableDatabase();
        goodsImg = new ContentValues();
        goodsInfo = new ContentValues();
        sharedPreferences = this.getSharedPreferences("upGoodsImage", MODE_PRIVATE);
        userInfo = this.getSharedPreferences("userInfo", MODE_PRIVATE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main:

                ChooseImageDialogUtil.showSelectSubmitDialog(this);

                break;
            case R.id.iv_sec1:
                ChooseImageDialogUtil.showSelectSubmitDialog(this, Properties.SET_SECIMG1);
                break;
            case R.id.iv_sec2:
                ChooseImageDialogUtil.showSelectSubmitDialog(this, Properties.SET_SECIMG2);
                break;
            case R.id.iv_sec3:
                ChooseImageDialogUtil.showSelectSubmitDialog(this, Properties.SET_SECIMG3);
                break;
            case R.id.iv_sec4:
                ChooseImageDialogUtil.showSelectSubmitDialog(this, Properties.SET_SECIMG4);
                break;
            case R.id.bt_submit_insert:
                if (ed_goodsId.getText().toString().isEmpty()){
                    Toast.makeText(this, "请填写货号", Toast.LENGTH_SHORT).show();
                }
                if (ed_goodsLabel.getText().toString().isEmpty()){
                    Toast.makeText(this, "请填写标签", Toast.LENGTH_SHORT).show();
                }
                if (ed_goodsPrice.getText().toString().isEmpty()){
                    Toast.makeText(this, "请填写价格", Toast.LENGTH_SHORT).show();
                }
                if (SetCount<5){
                    Toast.makeText(this, "图片未上传完整", Toast.LENGTH_SHORT).show();
                }
                if (
                        !ed_goodsId.getText().toString().isEmpty()&&
                        !ed_goodsLabel.getText().toString().isEmpty()&&
                        !ed_goodsPrice.getText().toString().isEmpty()&&
                        SetCount==5
                        )
                {


                    goodsInfo.put("goods_id",String.valueOf(System.currentTimeMillis()));
                    goodsInfo.put("goods_price",ed_goodsPrice.getText().toString());
                    goodsInfo.put("goods_label", ed_goodsLabel.getText().toString());
                    goodsInfo.put("user_id",userInfo.getString("user_id",""));
                    goodsInfo.put("goods_Name",ed_goodsId.getText().toString());
                    goodsImg.put("goods_id",String.valueOf(System.currentTimeMillis()));
                    goodsImg.put("goodsImg_mainpath",sharedPreferences.getString("goodsImg_mainpath",""));
                    goodsImg.put("goodsImg_sec1path",sharedPreferences.getString("goodsImg_sec1path",""));
                    goodsImg.put("goodsImg_sec2path",sharedPreferences.getString("goodsImg_sec2path",""));
                    goodsImg.put("goodsImg_sec3path",sharedPreferences.getString("goodsImg_sec3path",""));
                    goodsImg.put("goodsImg_sec4path",sharedPreferences.getString("goodsImg_sec4path",""));
                    writableDatabase.insert("goodsInfo", null, this.goodsInfo);
                    writableDatabase.insert("goodsImg", null, this.goodsImg);
                    writableDatabase.close();
                    finish();

                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isCheck = true;
        edit = sharedPreferences.edit();
        switch (requestCode) {
            case Properties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {

                    returnImgUtils("goodsImg_mainpath", iv_main, data, edit);
                    SetCount=SetCount+1;
                } else {
                    isCheck = false;
                }
                break;
            case Properties.SET_SECIMG1:
                if (resultCode == RESULT_OK) {
                    returnImgUtils("goodsImg_sec1path", iv_sec1, data, edit);
                    SetCount=SetCount+1;

                } else {
                    isCheck = false;
                }
                break;
            case Properties.SET_SECIMG2:
                if (resultCode == RESULT_OK) {
                    returnImgUtils("goodsImg_sec2path", iv_sec2, data, edit);
                    SetCount=SetCount+1;

                } else {
                    isCheck = false;
                }
                break;
            case Properties.SET_SECIMG3:
                if (resultCode == RESULT_OK) {
                    returnImgUtils("goodsImg_sec3path", iv_sec3, data, edit);
                    SetCount=SetCount+1;

                } else {
                    isCheck = false;
                }
                break;
            case Properties.SET_SECIMG4:
                if (resultCode == RESULT_OK) {
                    returnImgUtils("goodsImg_sec4path", iv_sec4, data, edit);
                    SetCount=SetCount+1;

                } else {
                    isCheck = false;
                }
                break;

        }
        if (isCheck) {
            edit.apply();
        } else {
            Toast.makeText(this, "取消修改", Toast.LENGTH_SHORT).show();
        }

    }
}
