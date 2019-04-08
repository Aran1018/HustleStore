package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.TrafficShowAdapter;
import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.manager.PublishContentManager;

import java.util.ArrayList;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/3/20.
 */


    public class SendGoodsActivity extends Activity implements View.OnClickListener {

    private Button bt_back;
    private RelativeLayout rl_titlebar;
    private RecyclerView rv_dealList;

    private SQLiteDatabase db;

    @Override
    protected void onResume() {
        GetData();
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendgoods);

        initView();


        GetData();
    }

    private void GetData() {
        PublishContentManager publishContentManager = new PublishContentManager(this);
        ArrayList<TrafficData> list = publishContentManager.getTrendsTrafficData();


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        //设置空隙处理方式为不处理--item乱跳问题
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Glide.with(this);
        rv_dealList.setLayoutManager(staggeredGridLayoutManager);
        TrafficShowAdapter adapter = new TrafficShowAdapter(this,list);
        adapter.setOnItemClickListener(new TrafficShowAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(View view, TrafficData data) {
                Intent intent = new Intent(SendGoodsActivity.this, MyPhotoDetailActivity.class);
                intent.putExtra("int_path",data.getPath());
                intent.putExtra("user_id",data.getUserId());
                intent.putExtra("publish_id",data.getPublish_id());
                startActivity(intent);
            }
        });
        rv_dealList.setAdapter(adapter);
    }

    private void initView() {
        rv_dealList = findViewById(R.id.rv_dealList);
        rl_titlebar = findViewById(R.id.rl_titlebar);
        rv_dealList = findViewById(R.id.rv_dealList);
//        bt_addGoods = findViewById(R.id.bt_addGoods);
        bt_back = findViewById(R.id.bt_back);
        rv_dealList.setOnClickListener(this);
//        bt_addGoods.setOnClickListener(this);
//        rl_titlebar.setOnClickListener(this);
        bt_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_titlebar:
            case R.id.bt_back:
                finish();
                break;
//            case R.id.bt_addGoods:
//                startActivity(new Intent(this,AddGoodsActivity.class));
        }
    }
}
