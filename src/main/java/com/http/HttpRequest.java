package com.http;


import com.CAPP;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.http.bean.SendRootBean;
import com.util.CUDES;
import com.util.CUNetwork;
import com.util.CUToast;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yebidaxiong on 2017/10/18.
 */

public class HttpRequest {

    public static void POST(int action, Map<String, Object> map, HttpCallBack callBack) {
        try {
            if (!CUNetwork.getInstance().isConnected()) {
                CUToast.getInstance().showErrorToast("请连接网络");
                callBack.onFailure("请连接网络");
                return;
            }
//            if (0==action){
//                return;
//            }
            if (null == map) {
                map = new HashMap<>();
            }
            callBack.setCallbackData();
            callBack.httpStart();
            SendRootBean ben = new SendRootBean("" + action);
            ben.setBody(map);
            JSONObject json = new JSONObject();
            json.put("root", ben);
            LogUtils.i(">] url=" + json.toJSONString());
            String encryptData = "";
            String encryption = "0";
            if (callBack.getIsEncryption()) {//加密
                encryption = "1";
                encryptData = CUDES.encrypt(json.toJSONString());
            } else {
                encryptData = json.toJSONString();
            }
            OkHttpUtils.postString().url(CAPP.getCommonAPP().isDebug() ? Constant.TEST_URL : Constant.BASE_URL)
                    .content(encryptData)
                    .mediaType(MediaType.parse("application/text;charset=UTF-8"))
//                    .addHeader("encryption",encryption)
//                    .addHeader("clientType","Android")
                    .build()
                    .execute(callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void file(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
//    public static void POST(String encryptData,HttpCallBack myCallBack){
//        LogUtils.i(">] url="+ DES.decrypt(encryptData));
//        OkHttpUtils.postString().url(Constant.BASE_URL)
//                .content(encryptData)
//                .mediaType(MediaType.parse("application/text;charset=utf-8"))
//                .build()
//                .execute(myCallBack);
//    }
// OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token")
//                .addParams("appid",C.wxAPP_ID)
//                .addParams("secret",C.wxAPP_secret)
//                .addParams("code",code)
//                .addParams("grant_type","authorization_code")
//                .build()
//                .execute(new StringCallback() {
//        @Override
//        public void onError(okhttp3.Call call, Exception e, int id) {
//            Bundle bundle=new Bundle();
//            bundle.putString("msg","微信登录网络错误");
//            loginCallBack.failure(bundle);
//        }
//
//        @Override
//        public void onResponse(String response, int id) {
//            LogUtils.i(">]response:"+response);
//            JSONObject accessTokenEntity = JSON.parseObject(response);
//            if(accessTokenEntity!=null){
//                getUserInfo(accessTokenEntity);
//            }else {
//                Bundle bundle=new Bundle();
//                bundle.putString("msg","微信登录网络错误");
//                loginCallBack.failure(bundle);
//            }
//        }
//    });
}
