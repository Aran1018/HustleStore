package com.triplebro.aran.hustlestore.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.MyListAdapter;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.beans.DealInfo;
import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.beans.GoodsImg;
import com.triplebro.aran.hustlestore.manager.GoodsInfoManager;
import com.triplebro.aran.hustlestore.manager.ListManager;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;
import com.triplebro.aran.hustlestore.utils.GetPathFromUri;
import com.triplebro.aran.hustlestore.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/31.
 */


public class DetailsActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout ll_point_box;
    private Banner bn_details;
    private List<String> imgLists;
    private Button bt_back;
    private RelativeLayout rl_back;
    private NestedScrollView sv_details;
    private int mDistanceY;
    private RelativeLayout rl_details_titlebar;
    private Button bt_detailActivity_deal;
    private String user_id;
    private String goods_id;
    private TextView tv_goodsName;
    private TextView tv_price;
    private TextView tv_goods_id;
    private Goods goods;
    private GoodsImg goodsImg;
    private DealInfo dealInfo;
    public static DetailsActivity instance;
    private RecyclerView rcv_findingList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        user_id = intent.getStringExtra("user_id");
        setContentView(R.layout.activity_details);
        initView();
        queryGoodsData();
        setOnclick();
        initImageList();
        getGoodsDataToDeal();

    }

    private void initView() {
        bt_back = findViewById(R.id.bt_back);
        rl_back = findViewById(R.id.rl_back);
        sv_details = findViewById(R.id.sv_details);
        bt_detailActivity_deal = findViewById(R.id.bt_detailActivity_deal);
        bt_detailActivity_deal.setOnClickListener(this);
        rl_details_titlebar = findViewById(R.id.rl_details_titlebar);
        bt_back.bringToFront();
        rl_back.bringToFront();
        rl_details_titlebar.bringToFront();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_details.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_details_titlebar);
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_back);
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,bt_back);
                }
            });
        }
        bn_details = findViewById(R.id.bn_details);
        tv_goodsName = findViewById(R.id.tv_goodsName);
        tv_price = findViewById(R.id.tv_price);
        tv_goods_id = findViewById(R.id.tv_goods_id);

        rcv_findingList = findViewById(R.id.rcv_findingList);


        queryList();
    }

    private void queryList() {
        List<ContextInfo> contextInfoList = new ListManager(this).getRandomContextInfoList();
        List<ContextInfo> fullContextInfoList = new ListManager(this).getFullContextInfoList(contextInfoList);
        MyListAdapter myListAdapter = new MyListAdapter(this, fullContextInfoList);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_findingList.setLayoutManager(manager);
        rcv_findingList.setAdapter(myListAdapter);
    }


    private void setOnclick() {

        bt_back.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        bt_detailActivity_deal.setOnClickListener(this);

    }

    private void initImageList(){
        imgLists = new ArrayList<String>();

        imgLists.add(GetPathFromUri.getPathByUri(Uri.parse(goodsImg.getGoodsImg_mainpath()), this));
        imgLists.add(GetPathFromUri.getPathByUri(Uri.parse(goodsImg.getGoodsImg_sec1path()), this));
        imgLists.add(GetPathFromUri.getPathByUri(Uri.parse(goodsImg.getGoodsImg_sec2path()), this));
        imgLists.add(GetPathFromUri.getPathByUri(Uri.parse(goodsImg.getGoodsImg_sec3path()), this));
        imgLists.add(GetPathFromUri.getPathByUri(Uri.parse(goodsImg.getGoodsImg_sec4path()), this));
        tv_goodsName.setText(goods.getGoods_name());
        tv_price.setText(goods.getGoods_price());
        bn_details.setImageLoader(new GlideImageLoader());
        bn_details.setImages(imgLists);
        bn_details.isAutoPlay(false);
        bn_details.setIndicatorGravity(BannerConfig.CENTER);
        bn_details.start();
    }
    private void queryGoodsData(){
        GoodsInfoManager goodsInfoManager = new GoodsInfoManager(this);
        goods = goodsInfoManager.queryGoodsDetails(goods_id);
        goodsImg = goodsInfoManager.queryGoodsImgDetails(goods_id);
    }

    private void getGoodsDataToDeal(){
        GoodsInfoManager goodsInfoManager = new GoodsInfoManager(this);
        dealInfo = goodsInfoManager.getDealInfo(goods.getGoods_id());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_detailActivity_deal:
                Intent intent = new Intent(this, CheckDealActivity.class);
                int user_id = goods.getUser_id();
                intent.putExtra("seller_id",user_id+"");
                intent.putExtra("buyer_id", dealInfo.getBuyer_id());
                intent.putExtra("goods_id", dealInfo.getGoods_id());
                intent.putExtra("order_date",dealInfo.getOrder_date());
                startActivity(intent);
                break;
        }
    }
}
