package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */

public class CUFormat {
    private CUFormat(){}
    private  static CUFormat intance;
    public  static CUFormat getInstance(){
        if (null==intance){
            intance=new CUFormat();
        }
        return  intance;
    }
    //校验手机号
    public  boolean isPhone(String str){
//        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        String regExp="^[1][3,4,5,6,7,8,9][0-9]{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    //校验身份证号
    public  boolean isIdentifyCardNum(String str){
//        String regExp="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
//        String regExp = "^\\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$";
        String regExp="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    //校验区号
    public  boolean isSeatPhone_first(String str){
        String regExp="\\d{3,4}";
        Pattern p=Pattern.compile(regExp);
        Matcher m=p.matcher(str);
        return m.matches();
    }
    //校验座机号码
    public  boolean isSeatPhone_last(String str){
        String regExp="\\d{7,8}";
        Pattern p=Pattern.compile(regExp);
        Matcher m=p.matcher(str);
        return m.matches();
    }

    /**
     * 验证邮箱地址是否正确
     * @param email
     * @return
     */
    public boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }

        return flag;
    }
}
