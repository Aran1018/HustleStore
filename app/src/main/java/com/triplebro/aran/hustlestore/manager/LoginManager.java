package com.triplebro.aran.hustlestore.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.services.CommunicationService;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/24.
 */


public class LoginManager implements ServiceConnection {

    private Context context;
    private String userPhone;
    private String passWord;

    public LoginManager(Context context, String userPhone, String passWord) {
        this.context = context;
        this.userPhone = userPhone;
        this.passWord = passWord;
    }

    public void login() {

        Intent intent = new Intent(context, CommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        CommunicationService.MyBinder myBinder =  (CommunicationService.MyBinder)service;

        myBinder.Login(context,userPhone,passWord);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
