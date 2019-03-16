package com.util;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.CAPP;
import com.http.bean.SendHeadBean;
import com.lib_common.R;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * 吐司的工具类
 */

public class CUToast {
    private  static long time=0;
//    View view=null;
//    TextView txt;
//    ImageView img;
//    Toast toast=null;
//    Handler handler;
//    Runnable runnable;
//    boolean isShow=false;
    private CUToast(){}
    private  static CUToast instance;
    public  static CUToast getInstance(){
        if (null==instance){
            instance=new CUToast();
        }
        return instance;
    }




    /**
     * m默认
     * @param text
     */
    public  void showToast( String text){
        if (!isShow()){
            return;
        }
        Toast toast= Toast.makeText(CAPP.getInstanc().getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        //        init();
//        if (isShow){
//            return;
//        }
//        txt.setText(text);
//        img.setImageResource(R.mipmap.toast1);
//        toast.show();
//        isShow=true;
//        toastCancel();
    }
    public  void showWarningToast( String text){
        if (!isShow()){
            return;
        }
        Toast toast= FancyToast.makeText(CAPP.getInstanc().getApplicationContext(),text,FancyToast.LENGTH_SHORT,FancyToast.WARNING,false);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public  void showSuccessToast( String text){
        if (!isShow()){
            return;
        }
        Toast toast= FancyToast.makeText(CAPP.getInstanc().getApplicationContext(),text,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public  void showErrorToast( String text){
        if (!isShow()){
            return;
        }
        Toast toast=  FancyToast.makeText(CAPP.getInstanc().getApplicationContext(),text,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

//        init();
//        if (isShow){
//            return;
//        }
//        txt.setText(text);
//        img.setImageResource(R.mipmap.toast2);
//        toast.show();
//        isShow=true;
//        toastCancel();
    }
    //    public  void showToast( String text,int type){
//        if (toast == null) {
//            toast = Toast.makeText(CommonAPP.getInstanc().getApplicationContext(), text, Toast.LENGTH_SHORT);
//            View view=View.inflate(CommonAPP.getInstanc().getApplicationContext(),R.layout.toast,null);
//            toast.setView(view);
//        } else {
//            toast.setText(text);
//            toast.setDuration(Toast.LENGTH_SHORT);
//        }
//        toast.show();
//    }
    public  void showToastBean(SendHeadBean head){
        if (200==head.getCode()){
            showSuccessToast(head.getText());
        }else{
            showErrorToast(head.getText());
        }
    }
//    public  void init(){
//        if (toast == null) {
//            toast = Toast.makeText(CAPP.getInstanc().getApplicationContext(), "", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER|Gravity.FILL_HORIZONTAL|Gravity.FILL_VERTICAL,0,0);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            view=View.inflate(CAPP.getInstanc().getApplicationContext(),R.layout.toast,null);
//            toast.setView(view);
//            txt=view.findViewById(R.id.txt);
//            img=view.findViewById(R.id.img);
//        }
//    }
//
//    private  void toastCancel(){
//        if (null==handler){
//            handler=new Handler(Looper.getMainLooper());
//            runnable=new Runnable() {
//                @Override
//                public void run() {
//                    toast.cancel();
//                    isShow=false;
//                }
//            };
//        }
//        handler.postDelayed(runnable,1000);
//    }
    public  boolean isShow(){
        boolean boo=false;
        if (0==time){
            time=System.currentTimeMillis();
            boo=true;
        }else if (System.currentTimeMillis()-time>2000){
            time=System.currentTimeMillis();
            boo=true;
        }
        return  boo;
    }
}
