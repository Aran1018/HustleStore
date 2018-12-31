package com.triplebro.aran.hustlestore.activites;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.databases.MyOpenHelper;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/27.
 */


public class ChangeDataActivity extends BaseActivity {

    private Button bt_back;
    private Button bt_commit;
    private EditText et_chang_name;
    private EditText et_chang_context;
    private SQLiteDatabase writableDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changedata);
        bt_back = findViewById(R.id.bt_back);
        bt_commit = findViewById(R.id.bt_commit);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_chang_name = findViewById(R.id.et_chang_name);
        et_chang_context = findViewById(R.id.et_chang_context);
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        writableDatabase = myOpenHelper.getWritableDatabase();
        SharedPreferences sharedPreferences = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");
        Cursor userLoginCursor = writableDatabase.query("userInfo", new String[]{"user_name", "user_introduction"}, "user_id = ?", new String[]{user_id}, null, null, null, null);
        if (userLoginCursor != null && userLoginCursor.getCount() > 0) {
            while (userLoginCursor.moveToNext()) {
                et_chang_name.setText(userLoginCursor.getString(0));
                et_chang_context.setText(userLoginCursor.getString(1));
            }
        }
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_name",et_chang_name.getText().toString());
                contentValues.put("user_introduction",et_chang_context.getText().toString());
                writableDatabase.update("userInfo",contentValues,null,null);
                writableDatabase.close();
                Intent intent = new Intent(ChangeDataActivity.this,SetupActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
//        et_chang_name.setText();
}
