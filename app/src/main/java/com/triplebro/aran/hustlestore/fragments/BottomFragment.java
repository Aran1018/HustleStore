package com.triplebro.aran.hustlestore.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.R;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/2.
 */


public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_tabbar;
    private LinearLayout ll_main;
    private LinearLayout ll_mine;
    private LinearLayout ll_message;
    private LinearLayout ll_list;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView tv_main;
    private TextView tv_list;
    private TextView tv_add;
    private TextView tv_message;
    private TextView tv_mine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_tabbar = inflater.inflate(R.layout.fragment_tabbar, null);
        initView();
        fragmentManager = getActivity().getFragmentManager();

        return fragment_tabbar;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_main:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new MainFragment());
                setBottomImage();
                fragment_tabbar.findViewById(R.id.btim_main).setBackgroundResource(R.drawable.yi_select);


                setBottomTextColor(tv_main);
                fragmentTransaction.commit();
                break;
//            case R.id.ll_add:
//
//                break;
            case R.id.ll_list:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top, new ListFragment());
                setBottomImage();
                fragment_tabbar.findViewById(R.id.btim_find).setBackgroundResource(R.drawable.find);
                setBottomTextColor(tv_list);

                fragmentTransaction.commit();
                break;
//            case R.id.ll_message:
//
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fl_top,new MessageFragment());
//                setBottomImage();
//
//                fragment_tabbar.findViewById(R.id.btim_chat).setBackgroundResource(R.drawable.chat);
//
//                setBottomTextColor(tv_message);
//
//                fragmentTransaction.commit();
//                break;
            case R.id.ll_mine:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_top,new MineFragment());
                setBottomImage();
                fragment_tabbar.findViewById(R.id.btim_mine).setBackgroundResource(R.drawable.mine);

                setBottomTextColor(tv_mine);

                fragmentTransaction.commit();
                break;
        }

    }

    public void initView(){
        ll_main = fragment_tabbar.findViewById(R.id.ll_main);
        ll_list = fragment_tabbar.findViewById(R.id.ll_list);
//        ll_message = fragment_tabbar.findViewById(R.id.ll_message);
        ll_mine = fragment_tabbar.findViewById(R.id.ll_mine);
        tv_main = fragment_tabbar.findViewById(R.id.tv_main);
        tv_list = fragment_tabbar.findViewById(R.id.tv_list);
//        tv_add = fragment_tabbar.findViewById(R.id.tv_add);
//        tv_message = fragment_tabbar.findViewById(R.id.tv_message);
        tv_mine = fragment_tabbar.findViewById(R.id.tv_mine);
        ll_main.setOnClickListener(this);
        ll_list.setOnClickListener(this);
//        ll_message.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
    }
    private void setBottomTextColor(TextView textView){
        tv_main.setTextColor(Color.parseColor("#FFC7C7C7"));
        tv_list.setTextColor(Color.parseColor("#FFC7C7C7"));
//        tv_message.setTextColor(Color.parseColor("#FFC7C7C7"));
        tv_mine.setTextColor(Color.parseColor("#FFC7C7C7"));
        textView.setTextColor(Color.parseColor("#000000"));

    }
    private void setBottomImage(){

        fragment_tabbar.findViewById(R.id.btim_mine).setBackgroundResource(R.drawable.mine_no_selected);
        fragment_tabbar.findViewById(R.id.btim_main).setBackgroundResource(R.drawable.yi);
        fragment_tabbar.findViewById(R.id.btim_find).setBackgroundResource(R.drawable.find_no_select);
//        fragment_tabbar.findViewById(R.id.btim_chat).setBackgroundResource(R.drawable.chat_no_selected);
//        fragment_tabbar.findViewById(R.id.btim_send).setBackgroundResource(R.drawable.send_no_selected);
    }
}
