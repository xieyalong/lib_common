package com.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.CAPP;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 前台后台信息
 * android系统信息
 * app版本信息
 * 厂商信息
 */
public class CUAPP {
    private CUAPP(){}
    private  static CUAPP intance;
    public  static CUAPP getInstance(){
        if (null==intance){
            intance=new CUAPP();
        }
        return  intance;
    }



    /**
     * 获取应用程序名称
     */
    public  String getAppName() {
        try{
            PackageManager packageManager = CAPP.getInstanc().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    CAPP.getInstanc().getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return CAPP.getInstanc().getResources().getString(labelRes);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    /**
     *版本号
     * 当前应用的版本名称
     * @return
     */
    public  String getAppVersionName(){
        PackageManager packageManager= CAPP.getInstanc().getPackageManager();
        PackageInfo packageInfo= null;
        try {
            packageInfo = packageManager.getPackageInfo(CAPP.getInstanc().getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionName;
    }

    /**
     * 获取应用code
     * @return
     */
    public  int getAppVersionCode(){
        PackageManager packageManager= CAPP.getInstanc().getPackageManager();
        PackageInfo packageInfo= null;
        try {
            packageInfo = packageManager.getPackageInfo(CAPP.getInstanc().getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionCode;
    }
    /**
     * 获取手机的IMEI串号
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     */
    public   String getSystemImei(){
        String imei= "0000000000000000";
        try {
            TelephonyManager telephonyManager= (TelephonyManager) CAPP.getInstanc().getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
            if (null==imei){
                imei=getMobileCPUSerial();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  imei;
    }


    /**
     * 获取手机厂商
     * @return  手机厂商
     */
    public  String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 手机型号
     * @return
     */
    public  String getSystemModel(){
        return android.os.Build.MODEL;
    }
    /**
     * sdk版本号
     * @return
     */
    public  int getSystemSdkVersion(){
        return  android.os.Build.VERSION.SDK_INT;
    }
    /**
     * 获取当前手机系统版本号
     * @return  系统版本号
     */
    public  String getSystemVersion() {
        return "Android"+android.os.Build.VERSION.RELEASE;
    }
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public  String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }
    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }
    /**
     *  查询手机内非系统应用
     * @return
     */
    public  List<PackageInfo> getAllApps() {

        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = CAPP.getInstanc().getPackageManager();
        // 获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            // 判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }
    public  String getMobileCPU(){
        return android.os.Build.CPU_ABI;
    }
    /**
     * 获取CPU序列号
     *
     * @return CPU序列号(16位)
     * 读取失败为"0000000000000000"
     */
    public static String getMobileCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                }else{
                    //文件结尾
                    break;
                }
            }
        } catch (IOException ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }
    /**
     * 返回当前的应用是否处于前台显示状态
     * @param packageName
     * @return
     */
    public   boolean isTopActivity(String packageName){
        //_context是一个保存的上下文
        ActivityManager am = (ActivityManager) CAPP.getInstanc().getApplicationContext().getSystemService(CAPP.getInstanc().ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if(list.size() == 0) return false;
        for(ActivityManager.RunningAppProcessInfo process:list){
           /* Log.d(getTAG(),Integer.toString(__process.importance));
            Log.d(getTAG(),__process.processName);*/
            if(process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    process.processName.equals(packageName)){
                return true;
            }
        }
        return false;
    }


    /**
     * 判断某个Activity 界面是否在前台
     * @param className 某个界面名称
     * @return
     */
    public  boolean  isForeground(String className) {
        if (TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) CAPP.getInstanc().getSystemService(CAPP.getInstanc().ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;

    }
    /**
     * 判断是否有Activity在运行
     */
    public  boolean isStackResumed() {
        ActivityManager manager = (ActivityManager) CAPP.getInstanc().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo runningTaskInfo = runningTaskInfos.get(0);
        return runningTaskInfo.numActivities > 1;
    }

    /**
     * 判断Service是否在运行
     */
    public  boolean isServiceRunning(Class<? extends Service> serviceClass) {
        ActivityManager manager = (ActivityManager) CAPP.getInstanc().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取application中指定的meta-data 调用方法时key就是UMENG_CHANNEL
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
    public  boolean isPhone(){
        if ("000000000000000".equals(getSystemImei())){
            return false;
        }
        return true;
    }
}
