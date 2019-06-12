package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.coder.zzq.smartshow.topbar.SmartTopbar;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.widget.CustomStatusView;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/11.
 */


public class DealSuccessActivity extends Activity {

    private CustomStatusView customStatusView;
    private Button bt_go_back_home;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SmartTopbar.setting().msgTextSizeSp(15);
        SmartTopbar.get(this).show("请点击回到主页");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_success);

        customStatusView = (CustomStatusView) findViewById(R.id.as_status);
        customStatusView.loadSuccess();
        bt_go_back_home = findViewById(R.id.bt_go_back_home);
        bt_go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealSuccessActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
