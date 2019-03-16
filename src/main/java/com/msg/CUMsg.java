package com.msg;

import android.app.Application;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import cn.leo.messenger.MagicMessenger;
import cn.leo.messenger.MessageCallback;

/**
 * @author xyl
 * @date 2018/05/09
 * @describe
 */
public class CUMsg {
    private static List<String> keys;
    public  static void init(Application app){
        MagicMessenger.init(app);
        keys=new ArrayList<>();
    }

    /**
     * 注销所有
     */
    public  static  void msgUnsubscribeALL(){
        if (null==keys){
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
            msgUnsubscribe(keys.get(i));
        }
    }

    /**
     * 发送信息
     * @param key
     * @param bundle
     */
    public static void sendMsg(String key,Bundle bundle) {
        MagicMessenger.post(key, bundle); //第一个参数为消息订阅标志,需要唯一
    }
    public static void sendMsg(String key,String data) {
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        MagicMessenger.post(key, bundle); //第一个参数为消息订阅标志,需要唯一
    }
    public static void sendMsg(String key) {
        MagicMessenger.post(key, new Bundle()); //第一个参数为消息订阅标志,需要唯一
    }
    /**
     * 手动注销
     * @param key
     * @param callback
     */
    public static void getMsg(final String key, final MsgCallback callback) {
        MagicMessenger.subscribe(key, new MessageCallback() {
            @Override
            public void onMsgCallBack(Bundle data) {
                callback.onMsgCallBack(data);
            }
        });
    }
    public static void getMsgString(final String key, final MsgCallback callback) {
        MagicMessenger.subscribe(key, new MessageCallback() {
            @Override
            public void onMsgCallBack(Bundle data) {
                callback.onMsgCallBack(data.getString("data"));
            }
        });
    }
    /**
     * 获取数据后会自动注销注册信息
     * @param key
     * @param callback
     */
    public static void getMsgUnsubscribe(final String key, final MsgCallback callback) {
        MagicMessenger.subscribe(key, new MessageCallback() {
            @Override
            public void onMsgCallBack(Bundle data) {
                callback.onMsgCallBack(data);
                MagicMessenger.unsubscribe(key);
            }
        });
    }

    /**
     * 注销
     * @param key
     */
    public static void msgUnsubscribe(String key) {
        MagicMessenger.unsubscribe(key);
    }
//    /**
//     * 选择注销
//     * @param key
//     * @param callback
//     */
//    public static void getMsgChooseUnsubscribe(final String key, final boolean isUnsubscribe, final MsgCallback callback) {
//        MagicMessenger.subscribe(key, new MessageCallback() {
//            @Override
//            public void onMsgCallBack(Bundle data) {
//                callback.onMsgCallBack(data);
//                if (isUnsubscribe){
//                    MagicMessenger.unsubscribe(key);
//                }
//            }
//        });
//    }

}
