package com.util;

import android.text.TextUtils;

import com.CAPP;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class CUStr {
    private CUStr(){}
    private static CUStr instance;
    public  static CUStr getInstance(){
        if (null==instance){
            instance=new CUStr();
        }
        return  instance;
    }
    public  static  String toJSONStringLog(Object object){
        if (!CAPP.getCommonAPP().isDebug()){
            return  null;
        }
        return JSONObject.toJSONString(object);
    }
    public  static  String toJSONString(Object object){
        return JSONObject.toJSONString(object);
    }
    public  static boolean jsonIsNull(String str){
        if ("{}".equals(str)||"[]".equals(str)){
            return true;
        }
        return false;
    }
    public  boolean listIsNull(List list){
        if (null==list||0==list.size()){
            return  true;
        }
        return  false;
    }
    /**
     * 将小数格式化为整数。金额。
     * @param price
     * @return
     */
    public  String getIntegerNumber(float price){
        DecimalFormat decimalFormat=new DecimalFormat("0");
        String p=decimalFormat.format(price);
        return p;
    }
    /**
     * 将String 类型的double数据转换成格式化后的百分比数据
     * @param doublevalue
     * @return
     */
    public  String formatDoublePer(String  doublevalue){
        String format ="";
        if(!TextUtils.isEmpty(doublevalue)){
            try {
                format = new DecimalFormat("#.##%").format(Double
                        .parseDouble(doublevalue));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    /**
     * 将String 类型的double数据转换成格式化后的百分比数据
     * @param doublevalue
     * @return
     */
    public  String formatDoublePerZero(String  doublevalue){
        String format ="";
        if(!TextUtils.isEmpty(doublevalue)){
            try {
                double s=Double.parseDouble(doublevalue)/100;
                format = new DecimalFormat("#.##%").format(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    public  String formatDoublePerZeroSame(String  doublevalue){
        String format ="";
        if(!TextUtils.isEmpty(doublevalue)){
            try {
                double s=Double.parseDouble(doublevalue);
                format = new DecimalFormat("#.##%").format(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    /**
     * 将String 类型的double数据转换成格式化后的百分比数据
     * @param floatValue   将0.0256转化成float类型的2.6
     * @return
     */
    public float formatFloat(String  floatValue){
        float data =0f;
        if(!TextUtils.isEmpty(floatValue)){
            try {
                DecimalFormat df = new DecimalFormat("0.00");
                float data1=Float.parseFloat(floatValue);
                data=Float.parseFloat(df.format(data1*100));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**将String类型的数据格式化  0.00补上*/
    public  String formatStringData(String str){
        String format="";
        if(!TextUtils.isEmpty(str)){
            try {
                float f = Float.parseFloat(str);
                DecimalFormat df = new DecimalFormat("0.00");
                format = df.format(f);
                if (format.contains(".")) {
                    int ind = format.indexOf(".");
                    if (format.substring(ind).length() < 2) {
                        format += "0";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    /**将String类型的数据格式化  0.00补上*/
    public  String formatStringPercentData100(String str){
        String format="";
        if(!TextUtils.isEmpty(str)){
            try {
                double f = Double.parseDouble(str)*100;
                DecimalFormat df = new DecimalFormat("0.00");
                format = df.format(f);
                if (format.contains(".")) {
                    int ind = format.indexOf(".");
                    if (format.substring(ind).length() < 2) {
                        format += "0";
                    }
                }
                format+="%";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    /**将String类型的数据格式化  0.00补上*/
    public  String formatStringPercentData(String str){
        String format="";
        if(!TextUtils.isEmpty(str)){
            try {
                float f = Float.parseFloat(str);
                DecimalFormat df = new DecimalFormat("0.00");
                format = df.format(f);
                if (format.contains(".")) {
                    int ind = format.indexOf(".");
                    if (format.substring(ind).length() < 2) {
                        format += "0";
                    }
                }
                format+="%";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    /**将String类型的数据格式化   *100  0.00补上*/
    public  String formatStringPercentData1(String str){
        String format="";
        if(!TextUtils.isEmpty(str)){
            try {
                float f = Float.parseFloat(str)*100;
                DecimalFormat df = new DecimalFormat("0.00");
                format = df.format(f);
                if (format.contains(".")) {
                    int ind = format.indexOf(".");
                    if (format.substring(ind).length() < 2) {
                        format += "0";
                    }
                }
                format+="%";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format;
    }



    public float formatFloat(float  floatValue){
        float data =0f;
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            data=Float.parseFloat(df.format(floatValue*100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public float formatFloatSame(float  floatValue){
        float data =0f;
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            data=Float.parseFloat(df.format(floatValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String formatFloatSame1(float  floatValue){
        String data ="";
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            data=df.format(floatValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public double formatFloatSame(double  floatValue){
        double data =0f;
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            data=Double.parseDouble(df.format(floatValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 将位数多的数据四舍五入  格式化
     * @param data
     * @return
     */
    public float fourFiveData(float data){
        float f1=0;
        try {
            BigDecimal b = new BigDecimal(data);
            f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f1;
    }
    /**
     * 将位数多的数据四舍五入  格式化
     * @param data
     * @return
     */
    public double fourFiveData(double data){
        float f1=0;
        try {
            BigDecimal b = new BigDecimal(data);
            f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f1;
    }

    /**
     * 随机数
     * @param length
     * @return
     */
    public   String createRandomCharData(int length)
    {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
                case 0:
                    data=randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                    sb.append((char)data);
                    break;
                case 2:
                    data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                    sb.append((char)data);
                    break;
            }
        }
        String result=sb.toString();
        return result;
    }
}
