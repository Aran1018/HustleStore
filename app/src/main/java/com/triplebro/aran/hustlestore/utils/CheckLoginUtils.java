package com.triplebro.aran.hustlestore.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/27.
 */


public class CheckLoginUtils {

    public static boolean checkLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");
        return !user_id.isEmpty();
    }

}
