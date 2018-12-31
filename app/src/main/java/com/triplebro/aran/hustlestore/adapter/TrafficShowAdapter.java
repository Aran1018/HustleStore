package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.beans.TrafficData;

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


public class TrafficShowAdapter extends RecyclerView.Adapter<TrafficShowAdapter.ViewHolder> {

    private ArrayList<TrafficData> list;
    private Context context;

    public TrafficShowAdapter(ArrayList<TrafficData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_imgtraffic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener( OnItemClickListener onItemClickListener) {
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void  onItemCLick(View view,TrafficData data);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_traffic;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_traffic = itemView.findViewById(R.id.iv_traffic);
        }

        public void bindData(final TrafficData data){
            Glide.with(context).load(data.path).into(iv_traffic);
            if (onItemClickListener!=null) {
                iv_traffic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemCLick(v,data);
                    }
                });

            }
        }
    }
}
