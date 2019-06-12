package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.MyPhotoDetailActivity;
import com.triplebro.aran.hustlestore.activites.SendGoodsActivity;
import com.triplebro.aran.hustlestore.beans.DealInfo;
import com.triplebro.aran.hustlestore.beans.Goods;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/11.
 */


public class OrderListRecycleAdapter extends RecyclerView.Adapter<OrderListRecycleAdapter.MyHolder> {
    Context context;
    List<DealInfo> dealInfoList;

    public OrderListRecycleAdapter(Context context, List<DealInfo> dealInfoList) {
        this.context = context;
        this.dealInfoList = dealInfoList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, final int position) {
        Glide.with(context).load(dealInfoList.get(position).getGoodsImg_mainpath()).bitmapTransform(new CropSquareTransformation(context)).into(myHolder.iv_goodsImg);
        myHolder.tv_deal_date.setText(dealInfoList.get(position).getOrder_date());
        myHolder.tv_deal_price.setText("￥" + dealInfoList.get(position).getGoods_price());
        myHolder.tv_goodsName.setText(dealInfoList.get(position).getGoods_name());
    }

    @Override
    public int getItemCount() {
        return dealInfoList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_goodsImg;
        private final TextView tv_goodsName;
        private final TextView tv_deal_date;
        private final TextView tv_deal_price;

        public MyHolder(View itemView) {
            super(itemView);
            iv_goodsImg = itemView.findViewById(R.id.iv_goodsImg);
            tv_goodsName = itemView.findViewById(R.id.tv_goodsName);
            tv_deal_date = itemView.findViewById(R.id.tv_deal_date);
            tv_deal_price = itemView.findViewById(R.id.tv_deal_price);


        }
    }

}
