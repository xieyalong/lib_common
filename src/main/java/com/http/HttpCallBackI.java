package com.http;


import com.alibaba.fastjson.JSONObject;
import com.http.bean.SendHeadBean;

public interface HttpCallBackI {
    void httpStart();
    void onStart();
    void onEnd();
    void onSuccess(String response);
    void onSuccessJson(JSONObject rootJson);
    void onFailure(String msg);
    void inProgress(float progress, long total, int id);
    void onSuccessJson(SendHeadBean head, JSONObject bodyJson);
}
