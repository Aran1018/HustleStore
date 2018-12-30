package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.DetailsActivity;
import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.GoodsImg;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;
import com.triplebro.aran.hustlestore.widget.RoundImageView;

import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/11.
 */


public class MainRecyclerVIewAdapter extends RecyclerView.Adapter<MainRecyclerVIewAdapter.MyHolder>implements View.OnClickListener {
    Context context;
    List<Goods> goodsList;
    List<GoodsImg> goodsImgs;

    public MainRecyclerVIewAdapter(Context context, List<Goods> list) {
        this.context = context;
        this.goodsList = goodsList;
        this.goodsImgs =  goodsImgs;
    }



    public void MainRecyclerVIewAdapterRefresh(List<Goods> goodsList, List<GoodsImg> goodsImgs){
        this.goodsList = goodsList;
        this.goodsImgs = goodsImgs;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {


//        String goods_name;
//        String user_Head;
//        String goods_price;
//        int goods_beLike;
//        int user_id;
//        int goods_id;
//        String goods_label;
//        String goodsImg_mainpath;
//        String goodsImg_sec1path;
//        String goodsImg_sec2path;
//        String goodsImg_sec3path;
//        String goodsImg_sec4path;

        String goods_name = goodsList.get(position).getGoods_name();

        String user_Head = goodsList.get(position).getUser_Head();
        String goods_price = goodsList.get(position).getGoods_price();
        int goods_beLike = goodsList.get(position).getGoods_beLike();
        String goods_label = goodsList.get(position).getGoods_label();
        holder.tv_userName.setText();
        holder.tv_goodsName.setText(goods_name);
        holder.tv_price.setText(goods_price);
        Glide.with(context).load(user_Head).bitmapTransform(new GlideCircleTransform(context)).into(holder.iv_userHead);



//        holder.iv_goodsimg.setImageResource(goodsImg);
//        holder.tv_userName.setText(userName);
//        holder.rv_userImg.setImageResource(userImg);

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_goodsimg:
            case R.id.tv_goodsName:
            case R.id.tv_userName:
            case R.id.tv_price:
            case R.id.iv_userHead:
            case R.id.ll_goodsView:
            case R.id.tv_beLike:
                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView iv_goodsimg;
        TextView tv_userName;
        TextView tv_price;
        ImageView iv_userHead;
        LinearLayout ll_goodsView;
        TextView tv_beLike;
       TextView tv_goodsName;

        private MyHolder(View itemView) {
            super(itemView);
            ll_goodsView = itemView.findViewById(R.id.ll_goodsView);
            iv_goodsimg = itemView.findViewById(R.id.iv_goodsimg);
            iv_userHead = itemView.findViewById(R.id.iv_userHead);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_goodsName = itemView.findViewById(R.id.tv_goodsName);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_beLike = itemView.findViewById(R.id.tv_beLike);
            setOnclick();
        }
        private void setOnclick(){
           iv_goodsimg.setOnClickListener(MainRecyclerVIewAdapter.this);
            ll_goodsView.setOnClickListener(MainRecyclerVIewAdapter.this);
            rv_userImg.setOnClickListener(MainRecyclerVIewAdapter.this);
            tv_goodsName.setOnClickListener(MainRecyclerVIewAdapter.this);
            tv_userName.setOnClickListener(MainRecyclerVIewAdapter.this);
            tv_price.setOnClickListener(MainRecyclerVIewAdapter.this);
            tv_beLike.setOnClickListener(MainRecyclerVIewAdapter.this);
        }
    }
}
