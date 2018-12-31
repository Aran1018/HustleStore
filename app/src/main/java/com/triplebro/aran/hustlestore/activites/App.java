package com.triplebro.aran.hustlestore.activites;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public Context getContext() {
        return context;
    }
}
