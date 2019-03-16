package com.util;

import android.content.Context;
import android.util.TypedValue;

import com.CAPP;

/**
 * 单位转换类
 */
public class CUDensity {
    private CUDensity(){}
    private  static CUDensity instance;
    public   static CUDensity getInstance(){
        if (null==instance){
            instance=new CUDensity();
        }
        return  instance;
    }
    /**
     * 得到设备的密度
     */
    public float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    /**
     * dp转px
     * @param dpVal
     * @return
     */
    public  int dp2px(float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxVal
     * @return
     */
    public  float px2dp(float pxVal){
        final float scale = CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public  int sp2px(float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, CAPP.getInstanc().getResources().getDisplayMetrics());
    }
    /**
     * 把密度转换为像素
     */
    public int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }


    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp( float pxValue) {
        final float fontScale = CAPP.getInstanc().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }



}
