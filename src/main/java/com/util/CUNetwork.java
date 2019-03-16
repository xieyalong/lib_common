package com.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.CAPP;

/**
 * 网络管理
 */
public class CUNetwork {
    private CUNetwork(){}
    private  static CUNetwork intance;
    public  static CUNetwork getInstance(){
        if (null==intance){
            intance=new CUNetwork();
        }
        return  intance;
    }
    /**
     * 判断网络是否连接
     * @return
     */
    public  boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager) CAPP.getInstanc()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断是否是wifi连接
     */
    public  boolean isWifi(){
        ConnectivityManager cm = (ConnectivityManager)  CAPP.getInstanc()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }
    /**
     * 打开网络设置界面
     */
    public  void openSetting(){
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        CUActivity.getIntanc().getActivity().startActivityForResult(intent, 0);
    }
    public  String getWifiName(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getSSID();
    }

    /**
     * 检查信号强弱
     */
    public  int checkWifiRssi() {
        WifiManager wifiManager = (WifiManager) CAPP.getInstanc().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        wifiInfo = wifiManager.getConnectionInfo();
        // 获得信号强度值
        // 0到-50表示信号最好，-50到-70表示信号偏差，小于-70表示最差，有可能连接不上或者掉线，一般Wifi已断则值为-200
        int level = wifiInfo.getRssi();
        // 根据获得的信号强度发送信息
        return level;
    }
    /**
     * 判断是否为cmwap，移动的为cmwap，联通的为3gwap、uniwap；
     * @return boolean
     */
    public  boolean detectWap() {
            ConnectivityManager cm = (ConnectivityManager) CAPP.getInstanc()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    if ((info.getTypeName().toLowerCase().indexOf("mobile") != -1)
                            && info.getExtraInfo() != null) {
                        // 移动网络；
                        if ((info.getExtraInfo().toLowerCase().indexOf("wap") != -1)) {
                            // 包含wap字符；
                            return true;
                        }
                    }
                }
            }
        return false;
    }
}
