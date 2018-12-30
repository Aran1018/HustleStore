package com.triplebro.aran.hustlestore.services;

import android.app.Activity;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.activites.FirstActivity;
import com.triplebro.aran.hustlestore.activites.MainActivity;
import com.triplebro.aran.hustlestore.beans.UserLogin;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.properties.Properties;
import com.triplebro.aran.hustlestore.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/26.
 */


public class CommunicationService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    public class MyBinder extends Binder {
        public void Login(Context context,String userPhone, String passWord) {
            CommunicationService.this.loginConnected(context,userPhone,passWord);
        }
    }

    private void loginConnected(final Context context, final String userPhone, final String passWord) {

        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("userPhone", userPhone);
        builder.add("password", passWord);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(Properties.loginAddress, builder, new Callback() {
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String registerCallbackString = null;
                        if (response.body() != null) {
                            registerCallbackString = response.body().string();
                        }
                        if (registerCallbackString .equals("{\"message\":1}")) {
                            Log.i("onResponse", "succ");
                            ((FirstActivity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                            SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("session", "success");
                            edit.commit();
                        } else {
                            Log.i("onResponse", "fail" + response);
                            ((FirstActivity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.equals("Response{protocol=http/1.1, code=500, message=, url=http://120.25.96.141:8080/login/login}\n"))
                                    Toast.makeText(context, "服务器正忙", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("onFailure", "fail" + e);
                        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
                        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
                        Cursor userLoginCursor = readableDatabase.query("userInfo", new String[]{"user_id", "pass_word"}, "user_id = ?", new String[]{userPhone}, null, null, null, null);
                        if (userLoginCursor != null && userLoginCursor.getCount() > 0){
                            while (userLoginCursor.moveToNext()){

                                if (!userLoginCursor.getString(0).equals(userPhone)){
                                    ((FirstActivity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "用户名错误", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                if (!userLoginCursor.getString(1).equals(passWord)){
                                    ((FirstActivity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else {

                                    SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sharedPreferences.edit();
                                    edit.putString("user_id",userPhone);
                                    edit.putString("pass_word",passWord);
                                    edit.commit();
                                    System.out.println("登录成功");
                                    Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                }
                            }
                        }else {
                            ((FirstActivity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "未注册", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                    }
                });


            }
        }.start();
    }


}
