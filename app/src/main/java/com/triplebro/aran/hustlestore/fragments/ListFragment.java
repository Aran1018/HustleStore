package com.triplebro.aran.hustlestore.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.AllPhotoDetailActivity;
import com.triplebro.aran.hustlestore.activites.FirstActivity;
import com.triplebro.aran.hustlestore.activites.MyPhotoDetailActivity;
import com.triplebro.aran.hustlestore.activites.SendNewsActivity;
import com.triplebro.aran.hustlestore.activites.TrafficUserShowActivity;
import com.triplebro.aran.hustlestore.adapter.LinerImageAdapter;
import com.triplebro.aran.hustlestore.adapter.MyListAdapter;
import com.triplebro.aran.hustlestore.beans.ContextInfo;
import com.triplebro.aran.hustlestore.beans.TrafficData;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.manager.ListManager;
import com.triplebro.aran.hustlestore.utils.AnimationUtils;
import com.triplebro.aran.hustlestore.utils.CheckLoginUtils;

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


public class ListFragment extends BaseFragment implements View.OnClickListener {

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
    private RecyclerView rcv_findingList;
    private boolean isGetData = false;
    private ArrayList<TrafficData> trafficDataArrayList;

    @Override
    public void onResume() {
        isGetData();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_list = inflater.inflate(R.layout.fragment_list, null);
        rcv_findingList = fragment_list.findViewById(R.id.rcv_findingList);


        NestedScrollView sv_item = fragment_list.findViewById(R.id.sv_item);
        initView();

        rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrafficUserShowActivity.class);
                startActivity(intent);
            }
        });
        isGetData();


        final RelativeLayout rl_titlebar = fragment_list.findViewById(R.id.rl_titlebar);
        rl_titlebar.bringToFront();
        bt_find_send.bringToFront();
        rl_find_send.bringToFront();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_item.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    AnimationUtils.TransparentUtils(mDistanceY, scrollY, rl_titlebar);
                    AnimationUtils.TransparentUtils(mDistanceY, scrollY, tv_find);
                    AnimationUtils.TransparentUtils(mDistanceY, scrollY, rl_find_send);
                    AnimationUtils.TransparentUtils(mDistanceY, scrollY, bt_find_send);
                }
            });
        }

        bt_find_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckLoginUtils.checkLogin(getActivity())) {
                    Intent intent = new Intent(getActivity(), SendNewsActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent1 = new Intent(getActivity(), FirstActivity.class);
                    startActivity(intent1);
                }

            }
        });


        return fragment_list;

    }

    private void isGetData() {
        List<ContextInfo> contextInfoList = new ListManager(getActivity()).getContextInfoList();
        List<ContextInfo> fullContextInfoList = new ListManager(getActivity()).getFullContextInfoList(contextInfoList);
        MyListAdapter myListAdapter = new MyListAdapter(getActivity(), fullContextInfoList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_findingList.setLayoutManager(manager);
        rcv_findingList.setAdapter(myListAdapter);
        queryDetailInfo();

    }

    private void queryDetailInfo() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(getActivity());
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();

        Cursor publishContent = readableDatabase.query("PublishContent", new String[]{"content_img","user_id","publish_id"}, null, null, null, null, "publish_time desc");
        if (publishContent != null && publishContent.getCount() > 0) {
            ArrayList<String> photos = new ArrayList<>();
            trafficDataArrayList = new ArrayList<>();
            while (publishContent.moveToNext()) {
                TrafficData trafficData = new TrafficData();
                photos.add(publishContent.getString(0));
                trafficData.setPath(publishContent.getString(0));
                trafficData.setUserId(publishContent.getString(1));
                trafficData.setPublish_id(publishContent.getString(2));
                trafficDataArrayList.add(trafficData);
            }

            ArrayList<ImageView> imageViews = new ArrayList<>();
            imageViews.add(iv_show1);
            imageViews.add(iv_show2);
            imageViews.add(iv_show3);
            imageViews.add(iv_show4);
            imageViews.add(iv_show5);
            LinerImageAdapter linerImageAdapter = new LinerImageAdapter(getActivity(),imageViews,trafficDataArrayList);
            linerImageAdapter.bindData();
//            if (photos.size() >= 5) {
//
//                Glide.with(this).load(photos.get(0)).bitmapTransform(new CropSquareTransformation(getActivity())).into(iv_show1);
//                Glide.with(this).load(photos.get(1)).bitmapTransform(new CropSquareTransformation(getActivity())).into(iv_show2);
//                Glide.with(this).load(photos.get(2)).bitmapTransform(new CropSquareTransformation(getActivity())).into(iv_show3);
//                Glide.with(this).load(photos.get(3)).bitmapTransform(new CropSquareTransformation(getActivity())).into(iv_show4);
//                Glide.with(this).load(photos.get(4)).bitmapTransform(new CropSquareTransformation(getActivity())).into(iv_show5);
//            }
        }

    }

    private void initView() {
        tv_find = fragment_list.findViewById(R.id.tv_find);
        rl_find_send = fragment_list.findViewById(R.id.rl_find_send);
        bt_find_send = fragment_list.findViewById(R.id.bt_find_send);
        rl_more = fragment_list.findViewById(R.id.rl_more);
        iv_show1 = fragment_list.findViewById(R.id.iv_show1);
        iv_show2 = fragment_list.findViewById(R.id.iv_show2);
        iv_show3 = fragment_list.findViewById(R.id.iv_show3);
        iv_show4 = fragment_list.findViewById(R.id.iv_show4);
        iv_show5 = fragment_list.findViewById(R.id.iv_show5);
        iv_show1.setOnClickListener(this);
        iv_show2.setOnClickListener(this);
        iv_show3.setOnClickListener(this);
        iv_show4.setOnClickListener(this);
        iv_show5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AllPhotoDetailActivity.class);
        switch (v.getId()) {
            case R.id.iv_show1:
                intent.putExtra("img_id",1);
                startActivity(intent);
                break;
            case R.id.iv_show2:
                intent.putExtra("img_id",2);
                startActivity(intent);
                break;
            case R.id.iv_show3:
                intent.putExtra("img_id",3);
                startActivity(intent);
                break;
            case R.id.iv_show4:
                intent.putExtra("img_id",4);
                startActivity(intent);
                break;
            case R.id.iv_show5:
                intent.putExtra("img_id",5);
                startActivity(intent);
                break;

        }
    }
}
