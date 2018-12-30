package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;
import com.triplebro.aran.hustlestore.fragments.BottomFragment;
import com.triplebro.aran.hustlestore.fragments.MainFragment;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_top,new MainFragment());
        fragmentTransaction.replace(R.id.fl_bottom,new BottomFragment());
        fragmentTransaction.commit();

//        setContentView(R.layout.activity_first);


    }

}
