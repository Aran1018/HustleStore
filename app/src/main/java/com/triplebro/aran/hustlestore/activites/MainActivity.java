package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.fragments.BottomFragment;
import com.triplebro.aran.hustlestore.fragments.MainFragment;
import com.triplebro.aran.hustlestore.utils.PermissionUtil;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.requestPower(this, ((Activity) this), "android.permission.WRITE_EXTERNAL_STORAGE");

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_top,new MainFragment());
        fragmentTransaction.replace(R.id.fl_bottom,new BottomFragment());
        fragmentTransaction.commit();

//        setContentView(R.layout.activity_first);


    }

}
