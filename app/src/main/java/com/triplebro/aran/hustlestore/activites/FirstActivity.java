package com.triplebro.aran.hustlestore.activites;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
 * Created by Aran on 2018/10/22.
 */


public class FirstActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_first;
    private RelativeLayout rl_login;
    private LinearLayout ll_login;

    private ImageView im_login_imgtext;
    private LinearLayout ll_ed_item;
    private Button bt_down;
    private Button bt_deletedrawable;
    private EditText ed_deleteedittext_phonenumber;
    private InputMethodManager imm;
    private TextView tv_forget_registe;
    private ViewPager vp_login;
    private  int Layouts[] = {R.layout.item_login,R.layout.item_register};
    private List<View> view_list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        setOnclick();
    }

    private void initView(){
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        ll_login = findViewById(R.id.ll_login);
        rl_first = findViewById(R.id.rl_first);
        rl_login = findViewById(R.id.rl_login);
        im_login_imgtext = findViewById(R.id.im_login_imgtext);
        ll_ed_item = findViewById(R.id.ll_ed_item);
        bt_down = findViewById(R.id.bt_down);
        vp_login = findViewById(R.id.vp_login);
        for (int i = 0; i <Layouts.length ; i++) {
            View v = getLayoutInflater().inflate(Layouts[i],null);
            view_list.add(v);
        }
        vp_login.setAdapter(new LoginViewpagerAdapter(view_list,Layouts,this));
    }

    private void setOnclick() {
        ll_login.setOnClickListener(this);
        bt_down.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_login:
                rl_first.setVisibility(View.GONE);
                rl_login.setVisibility(View.VISIBLE);
                imageMove();
                break;
            case R.id.bt_down:
                rl_first.setVisibility(View.VISIBLE);
                rl_login.setVisibility(View.GONE);
                hideALlSoftInput();
                break;

        }

    }

    private void imageMove() {
        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(im_login_imgtext, "scaleX", 1f, 0.6f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(im_login_imgtext, "scaleY", 1f, 0.6f);
        animatorSetsuofang.setDuration(1500);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSetsuofang.start();

        ObjectAnimator translationX = new ObjectAnimator().ofFloat(im_login_imgtext, "translationX", 0, -380f);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(im_login_imgtext, "translationY", 0, -420f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX, translationY); //设置动画
        animatorSet.setDuration(1500);  //设置动画时间
        animatorSet.start();

        ObjectAnimator edtranslationX = new ObjectAnimator().ofFloat(vp_login, "translationX", 0, 0);
        ObjectAnimator edtranslationY = new ObjectAnimator().ofFloat(vp_login, "translationY", 0, -420f);
        AnimatorSet edanimatorSet = new AnimatorSet();  //组合动画
        edanimatorSet.playTogether(edtranslationX, edtranslationY); //设置动画
        edanimatorSet.setDuration(1500);  //设置动画时间
        edanimatorSet.start();
    }

    public void hideALlSoftInput() {
        View view = this.getWindow().peekDecorView();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
