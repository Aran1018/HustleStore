package com.triplebro.aran.hustlestore.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.activites.MainActivity;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.manager.LoginManager;

import java.util.List;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/24.
 */


public class LoginViewpagerAdapter extends PagerAdapter {

    private List<View> view_list ;
    private int[] layouts;
    private TextView tv_forget_registe;
    private EditText ed_login_phonenumber;
    private EditText ed_login_password;
    private Context context;
    private Button bt_deletedrawable;
    private Button bt_submit_login;
    private Button bt_submit_register;
    private EditText ed_register_phonenumber;
    private EditText ed_register_password;
    private String username;
    private String password;

    public LoginViewpagerAdapter(List<View> view_list, int[] layouts,Context context) {
        this.context = context;
        this.view_list = view_list;
        this.layouts = layouts;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position){
        View v = view_list.get(position);
        container.addView(v);
        switch (position){
            case 0:
                tv_forget_registe = v.findViewById(R.id.tv_forget_registe);
                bt_submit_login = v.findViewById(R.id.bt_submit_login);
                bt_deletedrawable = v.findViewById(R.id.bt_deletedrawable);
                ed_login_phonenumber = v.findViewById(R.id.ed_login_phonenumber);
                ed_login_password = v.findViewById(R.id.ed_login_password);

                bt_submit_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_login_phonenumber.getText().toString().isEmpty()||ed_login_password.getText().toString().isEmpty()){
                            Toast.makeText(context, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                        }else {
                            LoginManager loginManager = new LoginManager(context,ed_login_phonenumber.getText().toString(),ed_login_password.getText().toString());
                            loginManager.login();
                        }
                    }
                });ed_login_phonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        switch (position){
                            case 0:
                                if (hasFocus) {
                                    bt_deletedrawable.setVisibility(View.VISIBLE);
                                    bt_deletedrawable.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v) {
                                            ed_login_phonenumber.setText("");
                                        }
                                    });
                                }
                                else {
                                    ed_login_phonenumber.setBackgroundResource(R.drawable.edittext_bg_light);
                                    bt_deletedrawable.setVisibility(View.GONE);
                                }
                                break;
                            case 1:
                                break;

                        }
                    }
                });
                break;
            case 1:
                bt_submit_register = v.findViewById(R.id.bt_submit_register);
                ed_register_phonenumber = v.findViewById(R.id.ed_register_phonenumber);
                ed_register_password = v.findViewById(R.id.ed_register_password);

                bt_submit_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
                        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
                        Cursor cursorUserId = writableDatabase.query("userInfo", new String[]{"user_id"}, "user_id = ?",
                                new String[]{ed_register_phonenumber.getText().toString()},
                                null, null, null, null);
                        if (cursorUserId.getCount()>0){
                            Toast.makeText(context, "您的账户已经注册请直接登陆", Toast.LENGTH_SHORT).show();
                        }else {
                            ContentValues cv = new ContentValues();
                            cv.put("user_id", ed_register_phonenumber.getText().toString());
                            cv.put("pass_word", ed_register_password.getText().toString());
                            long userRegister=writableDatabase.insert("userInfo",null, cv);
                            if(userRegister != -1){
                                SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString("user_id",ed_register_phonenumber.getText().toString());
                                edit.putString("pass_word",ed_register_password.getText().toString());
                                edit.commit();
                                writableDatabase.close();
                            }else {
                                System.out.println("error-----------------------数据库插入失败"+userRegister);
                                writableDatabase.close();
                            }
                            Intent intent = new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }

                    }
                });


                break;
        }


        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = view_list.get(position);
        container.removeView(v);

    }

    public void insertData(){

    }
}
