package com.triplebro.aran.hustlestore.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.WebViewActivity;
import com.triplebro.aran.hustlestore.adapter.BannerAdapter;
import com.triplebro.aran.hustlestore.adapter.MainRecyclerVIewAdapter;
import com.triplebro.aran.hustlestore.beans.Goods;
import com.triplebro.aran.hustlestore.manager.GoodsInfoManager;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;
import com.triplebro.aran.hustlestore.utils.GetPathFromUri;
import com.triplebro.aran.hustlestore.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/4.
 */


@SuppressLint("NewApi")
public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnScrollChangeListener, View.OnClickListener {

    private View fragment_main;
    /**
     * 轮播
     */
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;

    boolean AnimationisRunned = false;
    private Button bt_serch;
    private TextView tv_title_text;
    private RecyclerView rv_mainlist;
    private NestedScrollView sl_main;
    private Banner bn_main;
    private List<String> imgLists;
    private ImageView iv_newproduct;


    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_main = inflater.inflate(R.layout.fragment_main, null);
        initView();
        initBanner();
        setOnclickListener();
        return fragment_main;
    }

    private void initBanner() {
        imgLists = new ArrayList<String>();

        imgLists.add("/data/data/com.triplebro.aran.hustlestore/cache/imgs/ad/ad1.png");
        imgLists.add("/data/data/com.triplebro.aran.hustlestore/cache/imgs/ad/ad2.png");
        imgLists.add("/data/data/com.triplebro.aran.hustlestore/cache/imgs/ad/ad3.png");
        imgLists.add("/data/data/com.triplebro.aran.hustlestore/cache/imgs/ad/ad5.png");
        bn_main.setImageLoader(new GlideImageLoader());
        bn_main.setImages(imgLists);
        bn_main.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position==0||position==1){
                    startActivity(new Intent(getActivity(),WebViewActivity.class));
                }
            }
        });
        bn_main.isAutoPlay(true);
        bn_main.setIndicatorGravity(BannerConfig.CENTER);
        bn_main.start();
    }


    private void initView() {
        sl_main = fragment_main.findViewById(R.id.sl_main);
        tv_title_text = fragment_main.findViewById(R.id.tv_title_text);
        bt_serch = fragment_main.findViewById(R.id.bt_serch);
        bn_main = fragment_main.findViewById(R.id.bn_main);
        rv_mainlist = fragment_main.findViewById(R.id.rv_mainlist);
        iv_newproduct = fragment_main.findViewById(R.id.iv_newproduct);
        iv_newproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });
        Scroller scroller = new Scroller(getActivity());
        scroller.setFriction(1000);
        initData();

    }

    private void initData() {
        List<Goods> goodsList = new GoodsInfoManager(getActivity()).getGoodsList();
        List<Goods> goodsImg = new GoodsInfoManager(getActivity()).getGoodsImg(goodsList);
        List<Goods> userName = new GoodsInfoManager(getActivity()).getUserName(goodsImg);
        MainRecyclerVIewAdapter mainRecyclerVIewAdapter = new MainRecyclerVIewAdapter(getActivity(), userName);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rv_mainlist.setLayoutManager(manager);
        rv_mainlist.setAdapter(mainRecyclerVIewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void setOnclickListener() {
        bt_serch.setOnClickListener(this);
        sl_main.setOnScrollChangeListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_serch:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_LONG);
                break;
        }
    }




    private void initImageList() {
        imageResIds = new int[]{
                R.mipmap.goods_example,
                R.mipmap.goods_example,
                R.mipmap.goods_example,
                R.mipmap.goods_example,
                R.mipmap.goods_example
        };
        imageViewList = new ArrayList<ImageView>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getActivity());
            pointView.setBackgroundResource(R.mipmap.ic_launcher_round);
            layoutParams = new LinearLayout.LayoutParams(25, 25);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // 滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);
        previousSelectedPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 23 && 0 < scrollY) {
            if (oldScrollY < scrollY) {
                if (AnimationisRunned) {

                } else {
                    ScaledownAnimationUtils(tv_title_text);
                }
            }
        }
        // 启动动画            MainActivity.this.image.startAnimation(animationSet);
    }


    private void ScaledownAnimationUtils(View view) {
        AnimationUtils.ScaledownAnimationUtils(view);
        AnimationisRunned = true;
    }

}
