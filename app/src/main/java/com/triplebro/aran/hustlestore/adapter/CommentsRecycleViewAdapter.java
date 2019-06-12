package com.triplebro.aran.hustlestore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.beans.CommentInfo;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/9.
 */


public class CommentsRecycleViewAdapter extends RecyclerView.Adapter<CommentsRecycleViewAdapter.MyHolder>{
    Context context;
    List<CommentInfo> commentInfoList;

    public List<CommentInfo> getCommentInfoList() {
        return commentInfoList;
    }

    public void setCommentInfoList(List<CommentInfo> commentInfoList) {
        this.commentInfoList = commentInfoList;
        notifyDataSetChanged();
    }

    public CommentsRecycleViewAdapter(Context context, List<CommentInfo> commentInfoList) {
        this.context = context;
        this.commentInfoList = commentInfoList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int i) {
        String user_head = commentInfoList.get(i).getUser_Head();
        String comments_content = commentInfoList.get(i).getComments_content();
        String comments_date = commentInfoList.get(i).getComments_date();
        String user_name = commentInfoList.get(i).getUser_name();


        Glide.with(context).load(user_head).bitmapTransform(new GlideCircleTransform(context)).into(myHolder.im_user_img);
        myHolder.tv_data.setText(comments_date);
        myHolder.tv_user_content.setText(comments_content);
        myHolder.tv_userName.setText(user_name);
    }

    @Override
    public int getItemCount() {
        return commentInfoList.size();
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView im_user_img;
        private final TextView tv_userName;
        private final TextView tv_user_content;
        private final TextView tv_data;

        private MyHolder(View itemView) {
            super(itemView);

            im_user_img = itemView.findViewById(R.id.im_user_img);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_user_content = itemView.findViewById(R.id.tv_user_content);
            tv_data = itemView.findViewById(R.id.tv_data);

        }
    }
}
