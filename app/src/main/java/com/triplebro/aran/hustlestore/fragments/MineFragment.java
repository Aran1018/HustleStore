package com.triplebro.aran.hustlestore.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.AboutUsActivity;
import com.triplebro.aran.hustlestore.activites.AddGoodsActivity;
import com.triplebro.aran.hustlestore.activites.FirstActivity;
import com.triplebro.aran.hustlestore.activites.MyOrderActivity;
import com.triplebro.aran.hustlestore.activites.MycardActivity;
import com.triplebro.aran.hustlestore.activites.SendGoodsActivity;
import com.triplebro.aran.hustlestore.activites.SetupActivity;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.utils.CheckLoginUtils;
import com.triplebro.aran.hustlestore.utils.EasyGetGlideRoundImgUtils;
import com.triplebro.aran.hustlestore.widget.RoundImageView;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/4.
 */


public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View fragment_mine;
    private Button bt_set;
    private RelativeLayout rl_set;
    private LinearLayout ll_sendGoods;
    private LinearLayout ll_name;
    private ImageView rv_name;
    private TextView tv_name;
    private ImageView iv_name_in;
    private RelativeLayout rl_name_in;
    private String user_introduction;
    private String user_name;
    private RelativeLayout rl_sendGoods;
    private Button bt_sendGoods;
    private TextView tv_sendGoods;
    private LinearLayout ll_mySends;
    private LinearLayout sll_mySends;
    private TextView tv_mySends;
    private ImageView iv_mySends;
    private TextView tv_aboutus;
    private ImageView im_aboutus;
    private LinearLayout ll_aboutus;
    private LinearLayout sll_aboutus;
    private Button bt_myorder;
    private LinearLayout ll_myorder;
    private RelativeLayout rl_myorder;
    private TextView tv_myorder;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        fragment_mine = inflater.inflate(R.layout.fragment_mine, null);
        bt_set = fragment_mine.findViewById(R.id.bt_set);
        rl_set = fragment_mine.findViewById(R.id.rl_set);
        rv_name = fragment_mine.findViewById(R.id.rv_name);
        ll_name = fragment_mine.findViewById(R.id.ll_name);
        tv_name = fragment_mine.findViewById(R.id.tv_name);
        iv_name_in = fragment_mine.findViewById(R.id.iv_name_in);
        rl_name_in = fragment_mine.findViewById(R.id.rl_name_in);
        ll_sendGoods = fragment_mine.findViewById(R.id.ll_sendGoods);
        rl_sendGoods = fragment_mine.findViewById(R.id.rl_sendGoods);
        bt_sendGoods = fragment_mine.findViewById(R.id.bt_sendGoods);
        tv_sendGoods = fragment_mine.findViewById(R.id.tv_sendGoods);
        ll_mySends = fragment_mine.findViewById(R.id.ll_mySends);
        sll_mySends = fragment_mine.findViewById(R.id.sll_mySends);
        tv_mySends = fragment_mine.findViewById(R.id.tv_mySends);
        iv_mySends = fragment_mine.findViewById(R.id.iv_mySends);

        tv_aboutus = fragment_mine.findViewById(R.id.tv_aboutus);
        im_aboutus = fragment_mine.findViewById(R.id.im_aboutus);
        ll_aboutus = fragment_mine.findViewById(R.id.ll_aboutus);
        sll_aboutus = fragment_mine.findViewById(R.id.sll_aboutus);

        bt_myorder = fragment_mine.findViewById(R.id.bt_myorder);
        ll_myorder = fragment_mine.findViewById(R.id.ll_myorder);
        rl_myorder = fragment_mine.findViewById(R.id.rl_myorder);
        tv_myorder = fragment_mine.findViewById(R.id.tv_myorder);


        setOnclick();

        return fragment_mine;
    }

    private void initData() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(getActivity());
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");
        Cursor userLoginCursor = readableDatabase.query("userInfo", new String[]{"user_name","user_introduction"}, "user_id = ?", new String[]{user_id}, null, null, null, null);
        if (userLoginCursor != null && userLoginCursor.getCount() > 0) {
            while (userLoginCursor.moveToNext()) {
                user_name = userLoginCursor.getString(0);
                tv_name.setText(user_name);
                user_introduction = userLoginCursor.getString(1);
            }

        }
    }
    @Override
    public void onStart() {
        super.onStart();
        EasyGetGlideRoundImgUtils.readGlideRoundImg(getActivity(),rv_name);
        initData();

    }

    private void setOnclick() {
        bt_set.setOnClickListener(this);
        ll_name.setOnClickListener(this);
        rv_name.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        iv_name_in.setOnClickListener(this);
        rl_name_in.setOnClickListener(this);
        ll_sendGoods.setOnClickListener(this);
        rl_sendGoods.setOnClickListener(this);
        bt_sendGoods.setOnClickListener(this);
        tv_sendGoods.setOnClickListener(this);
        iv_mySends.setOnClickListener(this);
        ll_mySends.setOnClickListener(this);
        sll_mySends.setOnClickListener(this);
        tv_mySends.setOnClickListener(this);
        im_aboutus.setOnClickListener(this);
        ll_aboutus.setOnClickListener(this);
        sll_aboutus.setOnClickListener(this);
        tv_aboutus.setOnClickListener(this);
        bt_myorder.setOnClickListener(this);
        ll_myorder.setOnClickListener(this);
        rl_myorder.setOnClickListener(this);
        tv_myorder.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_set:
            case R.id.bt_set:
                if (CheckLoginUtils.checkLogin(getActivity())){

                    Intent intent = new Intent(getActivity(), SetupActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent1 = new Intent(getActivity(),FirstActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.ll_name:
            case R.id.rv_name:
            case R.id.tv_name:
            case R.id.iv_name_in:
            case R.id.rl_name_in:
                if(CheckLoginUtils.checkLogin(getActivity())){

                    Intent mycardIntent = new Intent(getActivity(), MycardActivity.class);
                    mycardIntent .putExtra("user_name",user_name);
                    mycardIntent .putExtra("user_introduction",user_introduction);
                    startActivity(mycardIntent);
                }else {
                    Intent intent1 = new Intent(getActivity(),FirstActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.ll_sendGoods:
            case R.id.rl_sendGoods:
            case R.id.bt_sendGoods:
            case R.id.tv_sendGoods:

                if (CheckLoginUtils.checkLogin(getActivity())){

                    Intent sendGoods = new Intent(getActivity(), AddGoodsActivity.class);
                    startActivity(sendGoods);
                }else {
                    Intent intent1 = new Intent(getActivity(),FirstActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.iv_mySends:
            case R.id.ll_mySends:
            case R.id.sll_mySends:
            case R.id.tv_mySends:
                Intent mySends = new Intent(getActivity(),SendGoodsActivity.class);
                startActivity(mySends);
                break;
            case R.id.im_aboutus:
            case R.id.ll_aboutus:
            case R.id.sll_aboutus:
            case R.id.tv_aboutus:
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_myorder:
            case R.id.ll_myorder:
            case R.id.rl_myorder:
            case R.id.tv_myorder:
                Intent intent1 = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
