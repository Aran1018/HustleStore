package com.triplebro.aran.hustlestore.utils;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ScrollView;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/14.
 */


public class AnimationUtils {


    public static void ScaledownAnimationUtils(View view){
//        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.5f, 1, 0.5f, 0, 0);
        ScaleAnimation titlescaleAnimation = new ScaleAnimation(1, 0.5f, 1, 1, 0, 0);
        scaleAnimation.setDuration(500);
        titlescaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(0);
        titlescaleAnimation.setRepeatCount(0);
        scaleAnimation.setFillAfter(true);
        titlescaleAnimation.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
//                animationSet.addAnimation(scaleAnimation);
        view.startAnimation(scaleAnimation);
    }
    public static void ScaleupAnimationUtils(View view){
//        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2, 0, 0);
        ScaleAnimation titlescaleAnimation = new ScaleAnimation(1, 2, 1, 1, 0, 0);
        scaleAnimation.setDuration(1000);
        titlescaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(0);
        titlescaleAnimation.setRepeatCount(0);
        scaleAnimation.setFillAfter(true);
        titlescaleAnimation.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
//                animationSet.addAnimation(scaleAnimation);
        view.startAnimation(scaleAnimation);
    }

    public static void TransparentUtils(int mDistanceY,int dy,View view){
        mDistanceY += dy;
        //toolbar的高度
        int toolbarHeight = view.getBottom();
        //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
        if (mDistanceY <= toolbarHeight) {
            float scale = (float) mDistanceY/toolbarHeight;
            float alpha =  255-(255 * scale);
            Log.i("TransparentUtils:scale",scale+"");
            Log.i("TransparentUtils:mD",mDistanceY+"");
            Log.i("TransparentUtils:dy",dy+"");
            view.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //将标题栏的颜色设置为完全不透明状态
            view.setBackgroundResource(R.color.touming);
            view.setBackgroundResource(R.color.touming);
        }
    }
    public static void TransparentUtils(int mDistanceY,int dy,TextView view){
        mDistanceY += dy;
        //toolbar的高度
        int toolbarHeight = view.getBottom();
        //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
        if (mDistanceY <= toolbarHeight) {
            float scale = (float) mDistanceY/toolbarHeight;
            float alpha =  255-(255 * scale);
            Log.i("TransparentUtils:scale",scale+"");
            Log.i("TransparentUtils:m",mDistanceY+"");
            view.setTextColor(Color.argb((int) alpha, 0, 0, 0));
        } else {
            //将标题栏的颜色设置为完全不透明状态
            view.setTextColor(Color.argb(0,0,0,0));
        }
    }



}
