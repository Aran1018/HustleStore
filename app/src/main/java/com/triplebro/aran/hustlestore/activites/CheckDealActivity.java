package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triplebro.aran.hustlestore.DBProvider.DatabaseProvider;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.UserInfo;
import com.triplebro.aran.hustlestore.manager.DealInfoManager;
import com.triplebro.aran.hustlestore.utils.GlideCircleTransform;

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


public class CheckDealActivity extends Activity implements View.OnClickListener {

    private Button bt_back;
    private RelativeLayout rl_back;
    private String seller_id;
    private String buyer_id;
    private String goods_id;
    private String order_date;
    private ImageView iv_goodsImg;
    private TextView tv_goodsName;
    private TextView tv_deal_date;
    private TextView tv_deal_price;
    private TextView tv_seller;
    private ImageView iv_seller_img;
    private Button submit_deal;
    private Goods goodsDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_deal);
        getTheIntent();
        initView();

    }

    private void initData() {

        DatabaseProvider databaseProvider = new DatabaseProvider(this);
        goodsDetails = databaseProvider.getGoodsDetails(goods_id);
        UserInfo dealUserNameAndHead = databaseProvider.getDealUserNameAndHead(seller_id);

        Glide.with(this).load(goodsDetails.getGoodsImg_mainpath()).placeholder(R.drawable.bg_userhead).bitmapTransform(new CropSquareTransformation(this)).into(iv_goodsImg);
        tv_goodsName.setText(goodsDetails.getGoods_name());
        tv_deal_price.setText("￥" + goodsDetails.getGoods_price());
        tv_deal_date.setText(order_date);

        Glide.with(this).load(dealUserNameAndHead.getUser_Head()).placeholder(R.drawable.bg_userhead).bitmapTransform(new GlideCircleTransform(this)).into(iv_seller_img);
        tv_seller.setText(dealUserNameAndHead.getUser_name());

    }

    private void getTheIntent() {
        Intent intent = getIntent();
        seller_id = intent.getStringExtra("seller_id");
        buyer_id = intent.getStringExtra("buyer_id");
        goods_id = intent.getStringExtra("goods_id");
        order_date = intent.getStringExtra("order_date");
    }

    private void initView() {
        iv_goodsImg = findViewById(R.id.iv_goodsImg);
        tv_goodsName = findViewById(R.id.tv_goodsName);
        tv_deal_date = findViewById(R.id.tv_deal_date);
        tv_deal_price = findViewById(R.id.tv_deal_price);

        iv_seller_img = findViewById(R.id.iv_seller_img);
        tv_seller = findViewById(R.id.tv_seller);

        submit_deal = findViewById(R.id.submit_deal);
        submit_deal.setOnClickListener(this);

        rl_back = findViewById(R.id.rl_back);
        bt_back = findViewById(R.id.bt_back);
        rl_back.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        initData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
            case R.id.bt_back:
                finish();
                break;
            case R.id.submit_deal:
                DealInfoManager dealInfoManager = new DealInfoManager(this);
                boolean b = dealInfoManager.setbeanToCVaddDeal(seller_id, buyer_id, goods_id, order_date, goodsDetails.getGoods_name(), goodsDetails.getGoodsImg_mainpath(), goodsDetails.getGoods_price());
                if (b) {
                    Intent intent = new Intent(this, DealSuccessActivity.class);
                    startActivity(intent);
                    finish();
                    DetailsActivity.instance.finish();
                } else {

                }
                break;

        }
    }
}
