package com.http.bean;


import android.text.TextUtils;

import com.CC;
import com.bean.BaseBean;
import com.util.CUAPP;
import com.util.CUSP;
import com.util.CUScreen;

/**
 * Created by lilfi on 2017/8/21.
 */

public class SendHeadBean extends BaseBean {
    public SendHeadBean(String action) {
        this.action = action;
    }

    public SendHeadBean() {
    }

    private String timestamp = "" + (System.currentTimeMillis() / 1000);// CommonUtilTime.getInstance().getTime_yyyy_MM_dd__HH_mm_ss();
    private String softver = "" + CUAPP.getInstance().getAppVersionCode();
    private String screen = CUScreen.getInstance().getScreenHeight() + "*" + CUScreen.getInstance().getScreenWidth();
    private String user_id = "";
    private String admin_id = "";
    private String os = CUAPP.getInstance().getSystemVersion();
    private String action = "";
    private String channel_id = "";//AndroidAppStore
    private String ua = CUAPP.getInstance().getDeviceBrand() + "-" + CUAPP.getInstance().getSystemModel();
    private String softname = CUAPP.getInstance().getAppVersionName();
    private String device_id = getDeviceId();
    private String session_id = "";
    private int code = 0;
    private String text = "";
    private String client_id = "Android";


    private String getDeviceId() {
        String key = "device_token";
        String device_id = CUSP.getInstance().getString(key);
        if (TextUtils.isEmpty(device_id)) device_id = "";
        return device_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSoftver() {
        return softver;
    }

    public void setSoftver(String softver) {
        this.softver = softver;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getUser_id() {

        if (null != CUSP.getInstance().getUserInfo()) {
            user_id = CUSP.getInstance().getUserInfo().getId();
        }
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getSoftname() {
        return softname;
    }

    public void setSoftname(String softname) {
        this.softname = softname;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getSession_id() {
        String _session_id = CUSP.getInstance().getString(CC.user_session_id);
        if (TextUtils.isEmpty(_session_id)) {
            session_id = "";
        } else {
            session_id = _session_id;
        }
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

//    {
//        "root":{
//        "body":{
//            "state_type":"1"
//        },
//        "head":{
//            "action":"41000",
//                    "admin_id":"",
//                    "channel_id":"",
//                    "client_id":"Android",
//                    "code":0,
//                    "device_id":"865032031900001",
//                    "os":"Android8.0.0",
//                    "screen":"1808*1080",
//                    "session_id":"",
//                    "softname":"1.0.0",
//                    "softver":"1",
//                    "text":"",
//                    "timestamp":"2018-11-07 14:16:37",
//                    "ua":"HUAWEI-MHA-AL00",
//                    "user_id":""
//        }
//    }
//    }
//    {
//        "root":{
//        "body":{
//            "state_type":"1"
//        },
//        "head":{
//            "action":"41000",
//                    "admin_id":"",
//                    "channel_id":"AndroidAppStore",
//                    "client_id":"Android",
//                    "code":0,
//                    "device_id":"865032031900001",
//                    "os":"Android8.0.0",
//                    "screen":"1808*1080",
//                    "session_id":"cjJraTFsSFJMdg==",
//                    "softname":"1.0.0",
//                    "softver":"1",
//                    "text":"",
//                    "timestamp":"2018-11-07 14:07:40",
//                    "ua":"HUAWEI-MHA-AL00",
//                    "user_id":"139"
//        }
//    }
//    }
}


