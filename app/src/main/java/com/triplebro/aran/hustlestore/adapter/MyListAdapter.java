package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.AllPhotoDetailActivity;
import com.triplebro.aran.hustlestore.activites.AllcardActivity;
import com.triplebro.aran.hustlestore.activites.UserCardActivity;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.utils.GeneralUtils;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/1/1.
 */


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<ContextInfo> list;
    private Context context;
    private List<TrafficData> trafficDataList;

    public MyListAdapter(Context context, List<ContextInfo> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_findings, parent, false);
        return new MyListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getContent_img()).bitmapTransform(new CropSquareTransformation(context)).into(holder.content_img);
        Glide.with(context).load(list.get(position).getUserHead()).bitmapTransform(new GlideCircleTransform(context)).into(holder.rvimg_list_userIcon);

        holder.tv_userName.setText(list.get(position).getUserName());
        holder.tv_user_introduction.setText(list.get(position).getUser_introduction());
        holder.tv_findings_date.setText(GeneralUtils.GetStringDate(list.get(position).getPublish_time()));
        String[] time=list.get(position).getPublish_time().split("   ");
        holder.tv_findings_time.setText(time[1].substring(0,5));
        holder.tv_publish_content.setText(list.get(position).getPublish_content());
        holder.bt_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllPhotoDetailActivity.class);
                intent.putExtra("int_path",list.get(position).getContent_img());
                intent.putExtra("publish_id",list.get(position).getPublish_id());
                intent.putExtra("user_id",list.get(position).getUser_id());
                context.startActivity(intent);
            }
        });

        holder.rvimg_list_userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserCardActivity.class);

                intent.putExtra("user_id",list.get(position).getUser_id());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    private MyListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemCLick(View view, ContextInfo data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_traffic;
        ImageView rvimg_list_userIcon;
        TextView tv_userName;
        TextView tv_user_introduction;
        ImageView content_img;
        TextView tv_findings_date;
        TextView tv_findings_time;
        private final LinearLayout ll_comments;
        private final Button bt_comments;
        private final TextView tv_publish_content;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_traffic = itemView.findViewById(R.id.iv_traffic);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            ll_comments = itemView.findViewById(R.id.ll_comments);
            bt_comments = itemView.findViewById(R.id.bt_comments);
            content_img = itemView.findViewById(R.id.iv_content_img);
            tv_findings_date = itemView.findViewById(R.id.tv_findings_date);
            tv_findings_time = itemView.findViewById(R.id.tv_findings_time);
            rvimg_list_userIcon = itemView.findViewById(R.id.rvimg_list_userIcon);
            tv_publish_content = itemView.findViewById(R.id.tv_publish_content);
            tv_user_introduction = itemView.findViewById(R.id.tv_user_introduction);
        }


        public void bindData(final ContextInfo data) {
            if (onItemClickListener != null) {
                iv_traffic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemCLick(v, data);
                    }
                });
            }
        }

    }
}
