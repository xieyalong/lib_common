package com.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;

/**
 * 监听activity
 */
public class CUActivity {
    private Application app;
    private Activity mActivity;
    private CUActivity(){}
    private  static CUActivity intanc;
    public  static CUActivity getIntanc(){
        if (null==intanc){
            intanc=new CUActivity();
        }
        return intanc;
    }
    public  Activity getActivity(){
        return this.mActivity;
    }
    public  void registerActivity(Application app){
        this.app=app;
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                mActivity=activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity=activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //com.yuanxinkangfu.omo2c.TestActivity
                LogUtils.i(">]Activity="+activity.getClass().getName());
                mActivity=activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {//监控进入和离开的状态
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

}
