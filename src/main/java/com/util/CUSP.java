package com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.CompoundButton;

import com.CAPP;
import com.alibaba.fastjson.JSONObject;
import com.bean.UserBean;

/**
 */

public class CUSP {
    private  static CUSP instance;
    private static SharedPreferences sp;
    private static final String FILE_NAME = "share_data";
    final  public  static  String uInfo="uInfo";
    UserBean userBean;
    public static CUSP getInstance() {
        if (null==instance){
            instance=new CUSP();
            sp= CAPP.getInstanc().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }
    public  void setUser(String str){
        CUSP.getInstance().putString(CUSP.uInfo,str);
    }

    public  void setUserNull(){
        putString(CUSP.uInfo,null);
        userBean=null;
    }
    public void putString(String key,String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public  String getString(String key){
        return sp.getString(key,null);
    }
    public void putInt(String key,int value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public  int getInt(String key){
        return sp.getInt(key,0);
    }
    public  int getInt(String key,int defValue){
        return sp.getInt(key,defValue);
    }
    public  void putBoolean(String key,boolean value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public  boolean getBoolean(String key,boolean defValue){
        return sp.getBoolean(key,defValue);
    }

    public UserBean getUserInfo(){
        try {
            String josnstr=getString(CUSP.uInfo);
            if (!TextUtils.isEmpty(josnstr)){
                userBean= JSONObject.parseObject(josnstr,UserBean.class);
                return  userBean;
            }else{
                userBean=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  userBean;
    }
    public  boolean isLogin(){
        UserBean userBean= getUserInfo();
        if (null==userBean||TextUtils.isEmpty(userBean.getId())){
            return  false;
        }
        return true;
    }
}
