package com.triplebro.aran.hustlestore.activites;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.triplebro.aran.hustlestore.R;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/22.
 */


public class LoginActivity extends Activity {

    private ImageView im_login_imgtext;
    private LinearLayout ll_ed_item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        im_login_imgtext = findViewById(R.id.im_login_imgtext);
        ll_ed_item = findViewById(R.id.ll_ed_item);
        imageMove();



    }


    private void imageMove(){
        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(im_login_imgtext, "scaleX", 1f, 0.6f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(im_login_imgtext, "scaleY", 1f, 0.6f);
        animatorSetsuofang.setDuration(1500);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSetsuofang.start();

        ObjectAnimator translationX = new ObjectAnimator().ofFloat(im_login_imgtext,"translationX",0,-250f);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(im_login_imgtext,"translationY",0,-400f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX,translationY); //设置动画
        animatorSet.setDuration(2000);  //设置动画时间
        animatorSet.start();

        ObjectAnimator edtranslationX = new ObjectAnimator().ofFloat(ll_ed_item,"translationX",0,0);
        ObjectAnimator edtranslationY = new ObjectAnimator().ofFloat(ll_ed_item,"translationY",0,-400f);
        AnimatorSet edanimatorSet = new AnimatorSet();  //组合动画
        edanimatorSet.playTogether(edtranslationX,edtranslationY); //设置动画
        edanimatorSet.setDuration(2000);  //设置动画时间
        edanimatorSet.start();
    }
}
