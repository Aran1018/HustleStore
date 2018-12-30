package com.triplebro.aran.hustlestore.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.TextView;

import com.triplebro.aran.hustlestore.BuildConfig;
import com.triplebro.aran.hustlestore.R;
import com.triplebro.aran.hustlestore.properties.Properties;

import java.io.File;

/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/12/27.
 */


public class ChooseImageDialogUtil {
    public static void showSelectSubmitDialog(final Context context) {
        PermissionUtil.requestPower(context, ((Activity) context), "android.permission.CAMERA");
        PermissionUtil.requestPower(context, ((Activity) context), "android.permission.WRITE_EXTERNAL_STORAGE");
        View view = View.inflate(context, R.layout.dialog_select_submit, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = view.findViewById(R.id.tv_select_gallery);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                ((Activity) context).startActivityForResult(photo_manager, Properties.FROM_GALLERY);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public static void showSelectSubmitDialog(final Context context, final int requestCode) {
        PermissionUtil.requestPower(context, ((Activity) context), "android.permission.CAMERA");
        PermissionUtil.requestPower(context, ((Activity) context), "android.permission.WRITE_EXTERNAL_STORAGE");
        View view = View.inflate(context, R.layout.dialog_select_submit, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = view.findViewById(R.id.tv_select_gallery);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                ((Activity) context).startActivityForResult(photo_manager, requestCode);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
