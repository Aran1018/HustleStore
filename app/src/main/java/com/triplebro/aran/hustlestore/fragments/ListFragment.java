package com.triplebro.aran.hustlestore.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.MainActivity;
import com.triplebro.aran.hustlestore.activites.SendNewsActivity;
import com.triplebro.aran.hustlestore.activites.TrafficUserShowActivity;
import com.triplebro.aran.hustlestore.adapter.FindTitleImgAdapter;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;

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


public class ListFragment extends Fragment {

    private View fragment_list;
    private int mDistanceY;
    private TextView tv_find;
    private RelativeLayout rl_find_send;
    private Button bt_find_send;
    private ImageView iv_show1;
    private ImageView iv_show2;
    private ImageView iv_show3;
    private ImageView iv_show4;
    private ImageView iv_show5;
    private RelativeLayout rl_more;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        fragment_list = inflater.inflate(R.layout.fragment_list, null);
        ScrollView sv_item = fragment_list.findViewById(R.id.sv_item);
        initView();
        rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrafficUserShowActivity.class);
                startActivity(intent);
            }
        });
        initImageData();
        final RelativeLayout rl_titlebar = fragment_list.findViewById(R.id.rl_titlebar);
        rl_titlebar.bringToFront();
        bt_find_send.bringToFront();
        rl_find_send.bringToFront();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_item.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_titlebar);
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,tv_find);
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,rl_find_send);
                    AnimationUtils.TransparentUtils(mDistanceY,scrollY,bt_find_send);
                }
            });
        }

        bt_find_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendNewsActivity.class);
                startActivity(intent);
            }
        });


        return fragment_list;

    }

    private void initView(){
        tv_find = fragment_list.findViewById(R.id.tv_find);
        rl_find_send = fragment_list.findViewById(R.id.rl_find_send);
        bt_find_send = fragment_list.findViewById(R.id.bt_find_send);
        iv_show1 = fragment_list.findViewById(R.id.iv_show1);
        iv_show2 = fragment_list.findViewById(R.id.iv_show2);
        iv_show3 = fragment_list.findViewById(R.id.iv_show3);
        iv_show4 = fragment_list.findViewById(R.id.iv_show4);
        iv_show5 = fragment_list.findViewById(R.id.iv_show5);
        rl_more = fragment_list.findViewById(R.id.rl_more);
    }
    private void initImageData() {
        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(iv_show1);
        imageViews.add(iv_show2);
        imageViews.add(iv_show3);
        imageViews.add(iv_show4);
        imageViews.add(iv_show5);
        List<String> strings = new ArrayList<String>();
        strings.add("/data/data/com.triplebro.aran.hustlestore/cache/user_show01/1.jpg");
        strings.add("/data/data/com.triplebro.aran.hustlestore/cache/user_show01/2.jpg");
        strings.add("/data/data/com.triplebro.aran.hustlestore/cache/user_show01/3.jpg");
        strings.add("/data/data/com.triplebro.aran.hustlestore/cache/user_show01/4.jpg");
        strings.add("/data/data/com.triplebro.aran.hustlestore/cache/user_show01/5.jpg");

        new FindTitleImgAdapter(getActivity(),imageViews,strings);
    }
}
