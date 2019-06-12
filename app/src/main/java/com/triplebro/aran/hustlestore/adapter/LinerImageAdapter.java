package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.activites.AllPhotoDetailActivity;
import com.triplebro.aran.hustlestore.activites.MyPhotoDetailActivity;
import com.triplebro.aran.hustlestore.beans.TrafficData;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/3/20.
 */


public class LinerImageAdapter {

    List<TrafficData> trafficDataList;
    List<ImageView> imageViews;
    Context context;
    public LinerImageAdapter(Context context,List<ImageView> imageViews , List<TrafficData> trafficDataList) {
        this.imageViews = imageViews;
        this.trafficDataList = trafficDataList;
        this.context = context;
    }

    /**
     * input:List<ImageView> imageViews , List<TrafficData> trafficDataList;
     * 方法描述：
     * 1.给控件绑定data
     * 2.设置点击事件
     * 3.把点击事件发送到下个页面
     *
     */
    public void bindData(){
        if (imageViews.size()>=5&&trafficDataList.size()>=5){
            for (int index=0;index<imageViews.size();index++){
                Glide.with(context).load(trafficDataList.get(index).getPath()).bitmapTransform(new CropSquareTransformation(context)).into(imageViews.get(index));
                sendData(imageViews.get(index),trafficDataList.get(index));
            }
        }
    }
    private void sendData(ImageView imageView, final TrafficData trafficData){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllPhotoDetailActivity.class);
                intent.putExtra("publish_id",trafficData.getPublish_id());
                intent.putExtra("user_id",trafficData.getUserId());
                intent.putExtra("int_path",trafficData.getPath());
                context.startActivity(intent);
            }
        });
    }

}
