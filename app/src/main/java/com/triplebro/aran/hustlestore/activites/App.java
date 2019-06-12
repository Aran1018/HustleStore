package com.triplebro.aran.hustlestore.activites;

import android.app.Application;
import android.content.Context;

import com.coder.zzq.smartshow.core.SmartShow;

public class App extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        SmartShow.init(this);
    }

    public Context getContext() {
        return context;
    }
}
