package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.adapter.BannerAdapter;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;
import com.triplebro.aran.hustlestore.utils.GetPathFromUri;
import com.triplebro.aran.hustlestore.utils.GlideImageLoader;
import com.triplebro.aran.hustlestore.utils.PermissionUtil;
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
    private ScrollView sv_details;
    private int mDistanceY;
    private RelativeLayout rl_details_titlebar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bt_back = findViewById(R.id.bt_back);
        rl_back = findViewById(R.id.rl_back);
        sv_details = findViewById(R.id.sv_details);
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
        setOnclick();
        bn_details = findViewById(R.id.bn_details);
        initImageList();

    }

    private void setOnclick() {

        bt_back.setOnClickListener(this);
        rl_back.setOnClickListener(this);

    }

    private void initImageList(){
        imgLists = new ArrayList<String>();
        String pathByUri = GetPathFromUri.getPathByUri(Uri.parse("content://media/external/images/media/549"), this);

        imgLists.add(pathByUri);
        imgLists.add(pathByUri);
        imgLists.add(pathByUri);
        imgLists.add(pathByUri);
        imgLists.add(pathByUri);
        bn_details.setImageLoader(new GlideImageLoader());
        bn_details.setImages(imgLists);
        bn_details.isAutoPlay(false);
        bn_details.setIndicatorGravity(BannerConfig.CENTER);
        bn_details.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
            case R.id.bt_back:
                finish();
                break;
        }
    }
}
