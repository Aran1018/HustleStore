package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.LoginViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/24.
 */


public class RegisterActivity extends Activity {

    private  int Layouts[] = {R.layout.item_login,R.layout.item_register};
    private ViewPager vp_login;
    private List<View> view_list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        vp_login = findViewById(R.id.vp_login);
        for (int i = 0; i <Layouts.length ; i++) {
            View v = getLayoutInflater().inflate(Layouts[i],null);
            view_list.add(v);
        }
        vp_login.setAdapter(new LoginViewpagerAdapter(view_list,Layouts,this));
    }
}
