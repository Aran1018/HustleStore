package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.triplebro.aran.hustlestore.R;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/8.
 */


public class AboutUsActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_about_us.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        iv_close_about_us = (ImageView) findViewById(R.id.iv_close_about_us);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_about_us:
                finish();
                break;
        }
    }
}
