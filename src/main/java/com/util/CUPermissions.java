package com.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.CAPP;
import com.CC;
import com.google.android.exoplayer2.C;

/**
 * 权限管理
 */
public class CUPermissions {
    private CUPermissions(){}
    private  static CUPermissions intance;
    public  static CUPermissions getInstance(){
        if (null==intance){
            intance=new CUPermissions();
        }
        return  intance;
    }
    public   void requestPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, new String[]{
                        android.Manifest.permission.CAMERA,//相机摄像头，录制视频
                        android.Manifest.permission.RECORD_AUDIO,//麦克风，录音
                        android.Manifest.permission.READ_PHONE_STATE,//文件读取
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,//外部储存器读取
                        android.Manifest.permission.CALL_PHONE,//拨打电话
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.CALL_PHONE,//打电话
                        android.Manifest.permission.REQUEST_INSTALL_PACKAGES
                },
                1);
        CUSP.getInstance().putString(CC.sp_permissions,"1");
    }
    public  void call(String mobile){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +mobile));
        if (ActivityCompat.checkSelfPermission(CAPP.getInstanc().getApplicationContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(CUActivity.getIntanc().getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        CUActivity.getIntanc().getActivity().startActivity(intent);
    }
}
