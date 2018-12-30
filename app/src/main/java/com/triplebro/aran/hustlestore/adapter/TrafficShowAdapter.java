package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.utils.GlideImageLoader;

import java.util.ArrayList;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/11/1.
 */


public class TrafficShowAdapter extends RecyclerView.Adapter {

    private ArrayList<String> list;
    private Context context;
    private ArrayList<Object> mHeights;


    public TrafficShowAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
        getRandomHight();
    }
    public void getRandomHight(){
        mHeights = new ArrayList<>();
        for(int i=0; i < list.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_imgtraffic, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof viewholder){
            ViewGroup.LayoutParams layoutParams = ((viewholder) holder).iv_traffic.getLayoutParams();
            layoutParams.height = (int) mHeights.get(position);
            layoutParams.width = (int) mHeights.get(position);
            ((viewholder) holder).iv_traffic.setLayoutParams(layoutParams);

            new GlideImageLoader().displayImage(context,list.get(position),((viewholder) holder).iv_traffic);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_traffic;

        public viewholder(View itemView) {
            super(itemView);

            iv_traffic = itemView.findViewById(R.id.iv_traffic);
        }
    }
}
