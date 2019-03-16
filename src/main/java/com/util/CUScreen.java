package com.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import com.CAPP;

/**
 * 屏幕管理
 */
public class CUScreen {
    private CUScreen(){}
    private  static CUScreen instance;
    public   static CUScreen getInstance(){
        if (null==instance){
            instance=new CUScreen();
        }
        return  instance;
    }


    //获取屏幕的宽度
    public  int getScreenWidth(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    //获取屏幕的高度
    public  int getScreenHeight(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
    //获取屏幕的密度
    public  float getScreenDensity(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics();
        return displayMetrics.density;
    }
    /**
     * 获取当前屏幕截图，包含状态栏
     * @return
     */
    public  Bitmap snapShotWithStatusBar(){
        View view = CUActivity.getIntanc().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width =getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * @return
     */
    public Bitmap snapShotWithoutStatusBar() {
        View view = CUActivity.getIntanc().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        CUActivity.getIntanc().getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

}
