package com.triplebro.aran.hustlestore.activites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coder.zzq.smartshow.core.SmartShow;
import com.coder.zzq.smartshow.topbar.ITopbarSetting;
import com.coder.zzq.smartshow.topbar.SmartTopbar;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.CommentsRecycleViewAdapter;
import com.triplebro.aran.hustlestore.beans.CommentInfo;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.manager.CommentsManager;
import com.triplebro.aran.hustlestore.utils.CommentDialog;
import com.triplebro.aran.hustlestore.utils.GeneralUtils;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.glide.transformations.CropSquareTransformation;

public class AllPhotoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String int_path;
    private String publish_id;
    private String user_id;
    private ImageView rvimg_list_userIcon;
    private TextView tv_userName;
    private TextView tv_user_introduction;
    private ImageView iv_content_img;
    private TextView tv_findings_date;
    private TextView tv_findings_time;
    private Button bt_back;
    private RelativeLayout comment;
    private RecyclerView rcv_comments;
    private TextView tv_publish_content;
    private CommentsRecycleViewAdapter commentsRecycleViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int_path = intent.getStringExtra("int_path");
        publish_id = intent.getStringExtra("publish_id");
        user_id = intent.getStringExtra("user_id");
        setContentView(R.layout.activity_photo_detail_all);
        initView();
        getComments();
    }

    private void queryDetailInfo() {

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        Cursor userInfo = readableDatabase.query("userInfo", new String[]{"user_name", "user_Head", "user_introduction"}, "user_id=?", new String[]{user_id}, null, null, null);
        if (userInfo != null && userInfo.getCount() > 0) {
            while (userInfo.moveToNext()) {
                tv_userName.setText(userInfo.getString(0));
                Glide.with(this).load(userInfo.getString(1)).bitmapTransform(new GlideCircleTransform(this)).into(rvimg_list_userIcon);
                tv_user_introduction.setText(userInfo.getString(2));
            }
        }
        Cursor publishContent = readableDatabase.query("PublishContent", new String[]{"publish_time", "publish_content"}, "publish_id=?", new String[]{publish_id}, null, null, null);
        if (publishContent != null && publishContent.getCount() > 0) {
            while (publishContent.moveToNext()) {
                tv_findings_date.setText(GeneralUtils.GetStringDate(publishContent.getString(0)));
                String[] time = publishContent.getString(0).split("   ");
                tv_publish_content.setText(publishContent.getString(1));
                tv_findings_time.setText(time[1].substring(0, 5));
            }
        }
    }

    private void initView() {
        rvimg_list_userIcon = findViewById(R.id.rvimg_list_userIcon);
        tv_userName = findViewById(R.id.tv_userName);
        tv_user_introduction = findViewById(R.id.tv_user_introduction);
        tv_findings_date = findViewById(R.id.tv_findings_date);
        tv_findings_time = findViewById(R.id.tv_findings_time);
        iv_content_img = findViewById(R.id.iv_content_img);
        tv_publish_content = findViewById(R.id.tv_publish_content);
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(this);

        comment = (RelativeLayout) findViewById(R.id.comment);
        comment.setOnClickListener(this);


        rcv_comments = findViewById(R.id.rcv_comments);
    }

    private void getComments() {

        List<CommentInfo> commentInfos = new CommentsManager(this).queryCommentInfoList(publish_id);
        commentsRecycleViewAdapter = new CommentsRecycleViewAdapter(AllPhotoDetailActivity.this, commentInfos);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_comments.setLayoutManager(manager);
        rcv_comments.setAdapter(commentsRecycleViewAdapter);
    }


    private void initEvent() {
        Glide.with(this).load(int_path).bitmapTransform(new CropSquareTransformation(this)).into(iv_content_img);
        queryDetailInfo();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initEvent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.comment:
                showCommentDialog();
                break;

        }
    }

    private void showCommentDialog() {
        new CommentDialog("优质评论将会被优先展示", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                new CommentsManager(AllPhotoDetailActivity.this).addCommentd(publish_id, inputText.trim());
                getComments();
            }
        }).show(getSupportFragmentManager(), "comment");
    }
}
