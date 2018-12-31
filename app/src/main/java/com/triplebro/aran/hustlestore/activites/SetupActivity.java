package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.utils.CheckLoginUtils;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/31.
 */


public class SetupActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_logout;
    private Button bt_back;
    private LinearLayout ll_changeData;
    private RelativeLayout rl_changeDataContext;
    private ImageView im_changData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        bt_back = findViewById(R.id.bt_back);
        bt_logout = findViewById(R.id.bt_logout);
        ll_changeData = findViewById(R.id.ll_changeData);
        im_changData = findViewById(R.id.im_changData);
        rl_changeDataContext = findViewById(R.id.rl_changeDataContext);
        bt_back.setOnClickListener(this);
        ll_changeData.setOnClickListener(this);
        im_changData.setOnClickListener(this);
        rl_changeDataContext.setOnClickListener(this);
        bt_logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_logout:
                Intent intent = new Intent(SetupActivity.this,FirstActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                SharedPreferences sharedPreferences = this.getSharedPreferences("userInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);

                break;
            case R.id.ll_changeData:
            case R.id.rl_changeDataContext:
            case R.id.im_changData:
                if (CheckLoginUtils.checkLogin(this)){

                    Intent intent1 = new Intent(SetupActivity.this,ChangeDataActivity.class);
                    startActivity(intent1);
                }else {

                    Intent intent1 = new Intent(SetupActivity.this,FirstActivity.class);
                    startActivity(intent1);
                }
                break;
        }
    }
}
