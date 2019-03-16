package com.util;

import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;

public class CULog {
    private CULog(){}
    private  static CULog intance;
    public  static CULog getI(){
        if (null==intance){
            intance=new CULog();
        }
        return  intance;
    }
    public  void i(String str){
        LogUtils.i(str);
    }
    public  void i_toJSONString(String tag,Object object){
        LogUtils.i(tag+""+JSONObject.toJSONString(object));
    }
    public  void json(String strJson){
        LogUtils.json(strJson);
    }
    public  void json(String tag,Object objJson){
        LogUtils.tag(tag);
        LogUtils.json(JSONObject.toJSONString(objJson));
    }
}
