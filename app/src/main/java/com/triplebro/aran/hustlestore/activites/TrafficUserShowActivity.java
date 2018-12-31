package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.TrafficShowAdapter;
import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;

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


public class TrafficUserShowActivity extends Activity {

    private RecyclerView rv_usershowtraffic;
    private RelativeLayout rl_back;
    private Button bt_back;
    private int mDistanceY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usershow_traffic);

        rv_usershowtraffic = findViewById(R.id.rv_usershowtraffic);
        rl_back = findViewById(R.id.rl_back);
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_back.bringToFront();
        bt_back.bringToFront();
        ArrayList<TrafficData> list = new ArrayList<TrafficData>();
//        for(int i=0;i<6;i++){
//
//            list.add("/data/data/com.triplebro.aran.hustlestore/cache/images/shoes_user_show01/1.jpg");
//            list.add("/data/data/com.triplebro.aran.hustlestore/cache/images/shoes_user_show01/2.jpg");
//            list.add("/data/data/com.triplebro.aran.hustlestore/cache/images/shoes_user_show01/3.jpg");
//            list.add("/data/data/com.triplebro.aran.hustlestore/cache/images/shoes_user_show01/4.jpg");
//            list.add("/data/data/com.triplebro.aran.hustlestore/cache/images/shoes_user_show01/5.jpg");
//        }
        for (int i = 0; i < 30; i++) {
            TrafficData trafficData = new TrafficData();
            trafficData.path=R.mipmap.goods_example;
            list.add(trafficData);
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        //设置空隙处理方式为不处理--item乱跳问题
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        Glide.with(this);
        rv_usershowtraffic.setLayoutManager(staggeredGridLayoutManager);
        TrafficShowAdapter adapter = new TrafficShowAdapter(list,this);
        rv_usershowtraffic.setAdapter(adapter);
        adapter.setOnItemClickListener(new TrafficShowAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(View view, TrafficData data) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rv_usershowtraffic.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    AnimationUtils.TransparentUtils(mDistanceY, dy, rl_back);
                    AnimationUtils.TransparentUtils(mDistanceY, dy, bt_back);
                }
            });
        }


    }
}
