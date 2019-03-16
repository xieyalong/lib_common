package com.http;

import android.content.Context;
import android.net.ConnectivityManager;

import com.CAPP;
//
//import com.wuyueshangshui.yuanxinkangfudoctor.R;
//import com.wuyueshangshui.yuanxinkangfudoctor.util.QMUIUtils;
//import com.wuyueshangshui.yuanxinkangfudoctor.util.ToastUtil;

/**
 * Created by lilfi on 2017/8/22.
 */

public class CheckHttp {
    public static boolean netIsOk(Context context){
        ConnectivityManager cm= (ConnectivityManager) CAPP.getInstanc().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo()!=null){
            return cm.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }


    public static void NetError(Context mContext){
//        if (!CheckHttp.netIsOk(mContext)){
//            ToastUtil.getShortToastByString(mContext.getResources().getString(R.string.please_check_network));
//        }
//        QMUIUtils.dismissLoading(mContext);
    }
    //检测网络是否连接，检测请求的错误码。
    public static void NetError(Context mContext,int errorCode){
//        QMUIUtils.dismissLoading(mContext);
//        if (!CheckHttp.netIsOk(mContext)){
//            ToastUtil.getShortToastByString(mContext.getResources().getString(R.string.please_check_network));
//            return;
//        }
//        switch (errorCode){
//            case 404:
//                ToastUtil.getShortToastByString(mContext.getResources().getString(R.string.request_address_not_exist));
//                break;
//            case 500:
//                ToastUtil.getShortToastByString(mContext.getResources().getString(R.string.service_reponse_error));
//                break;
//            case 504:
//                ToastUtil.getShortToastByString(mContext.getResources().getString(R.string.request_timeout));
//                break;
//        }

    }

    public static void NetErrorLogin(Context mContext,int errorCode){
//        QMUIUtils.dismissLoading(mContext);
//        if (!CheckHttp.netIsOk(mContext)){
//            ToastUtil.getLongToastLogin(mContext,mContext.getResources().getString(R.string.please_check_network));
//
//            return;
//        }
//        switch (errorCode){
//            case 404:
//                ToastUtil.getLongToastLogin(mContext,mContext.getResources().getString(R.string.request_address_not_exist));
//                break;
//            case 500:
//                ToastUtil.getLongToastLogin(mContext,mContext.getResources().getString(R.string.service_reponse_error));
//                break;
//            case 504:
//                ToastUtil.getLongToastLogin(mContext,mContext.getResources().getString(R.string.request_timeout));
//                break;
//        }

    }
}
