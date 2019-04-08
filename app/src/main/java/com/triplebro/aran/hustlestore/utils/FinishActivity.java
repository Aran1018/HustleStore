package com.triplebro.aran.hustlestore.utils;

import android.content.Intent;

import com.triplebro.aran.hustlestore.activites.MainActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2019/3/28.
 */


public class FinishActivity extends CordovaPlugin {
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        if ("jumpNative".equals(action)) {
            Intent intent = new Intent(this.cordova.getActivity(), MainActivity.class);
            this.cordova.getActivity().startActivity(intent);
            return true;
        }
        return false;
    }
}
