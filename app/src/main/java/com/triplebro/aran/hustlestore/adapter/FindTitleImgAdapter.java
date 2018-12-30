package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.triplebro.aran.hustlestore.utils.GlideImageLoader;

import java.util.ArrayList;
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


public class FindTitleImgAdapter {

    Context context;
    List<ImageView> imageViews;
    List<String> datalist;

    public FindTitleImgAdapter(Context context, List<ImageView> imageViews, List<String> datalist) {
        this.context = context;
        this.imageViews = imageViews;
        this.datalist = datalist;
        for(int i=0;i<imageViews.size();i++){
            new GlideImageLoader().displayImage(context,datalist.get(i),imageViews.get(i));
        }
    }

}
