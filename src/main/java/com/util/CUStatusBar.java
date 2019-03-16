package com.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;

/**
 *状态栏设置
 */
public class CUStatusBar {
    private  static CUStatusBar instance;
    public   static CUStatusBar getInstance(){
        if (null==instance){
            instance=new CUStatusBar();
        }
        return  instance;
    }
    /**
     * 状态栏去掉背景颜色
     */
    public void setStatusBar(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
                View decorView = activity.getWindow().getDecorView();
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
                WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            }
            //修改字体颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 状态栏隐藏
     * @param activity
     */
    @Deprecated
    public  void setStatusBarHidden(Activity activity){
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 状态栏隐藏，和隐藏ActionBar，不受dialog影响
     * @param context
     * @param actionBar
     * @param statusBar
     */
    public static void hideSupportActionBar(Context context, boolean actionBar, boolean statusBar) {
        if (actionBar) {
            AppCompatActivity appCompatActivity = CommonUtil.getAppCompActivity(context);
            if (appCompatActivity != null) {
                ActionBar ab = appCompatActivity.getSupportActionBar();
                if (ab != null) {
                    ab.setShowHideAnimationEnabled(false);
                    ab.hide();
                }
            }
        }
        if (statusBar) {
            if (context instanceof FragmentActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                fragmentActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else {
                CommonUtil.getAppCompActivity(context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }

    /**
     * 显示 状态栏和ActionBar
     * @param context
     * @param actionBar
     * @param statusBar
     */
    public static void showSupportActionBar(Context context, boolean actionBar, boolean statusBar) {
        if (actionBar) {
            AppCompatActivity appCompatActivity = CommonUtil.getAppCompActivity(context);
            if (appCompatActivity != null) {
                ActionBar ab = appCompatActivity.getSupportActionBar();
                if (ab != null) {
                    ab.setShowHideAnimationEnabled(false);
                    ab.show();
                }
            }
        }

        if (statusBar) {
            if (context instanceof FragmentActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                fragmentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else {
                CommonUtil.getAppCompActivity(context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }
}
