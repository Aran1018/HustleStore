package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.OrderListRecycleAdapter;
import com.triplebro.aran.hustlestore.beans.DealInfo;

import java.util.List;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/4/12.
 */


public class MyOrderActivity extends Activity {

    private Button bt_back;
    private RecyclerView rcv_orders;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        initView();

        initData();
    }

    private void initData() {

        DatabaseProvider databaseProvider = new DatabaseProvider(this);
        List<DealInfo> mydealList = databaseProvider.getMydealList();
        OrderListRecycleAdapter orderListRecycleAdapter = new OrderListRecycleAdapter(this,mydealList);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_orders.setLayoutManager(manager);
        rcv_orders.setAdapter(orderListRecycleAdapter);

    }

    private void initView() {
        bt_back = findViewById(R.id.bt_back);
        rcv_orders = findViewById(R.id.rcv_orders);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
