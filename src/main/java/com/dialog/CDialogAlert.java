package com.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.util.CUActivity;

public class CDialogAlert {
    private  AlertDialog alertDialog;
    private CDialogAlert(){}

    private  static CDialogAlert intance;
    public  static CDialogAlert getInstance(){
        if (null==intance){
            intance=new CDialogAlert();
        }
        return  intance;
    }

    public   void showAlert(String info,DialogInterface.OnClickListener positiveButton) {
        try {
            if (null!=alertDialog&&alertDialog.isShowing()){
                return;
            }
            alertDialog=new AlertDialog.Builder(CUActivity.getIntanc().getActivity())
                    .setMessage(info)
                    .setCancelable(false)
                    .setPositiveButton("确定",positiveButton)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public   void showAlert(String info,DialogInterface.OnClickListener positiveButton,DialogInterface.OnClickListener negativeButton) {
        if (null!=alertDialog&&alertDialog.isShowing()){
            return;
        }
        alertDialog= new AlertDialog.Builder(CUActivity.getIntanc().getActivity())
                .setMessage(info)
                .setCancelable(false)
                .setPositiveButton("确定",positiveButton)
                .setNegativeButton("取消",negativeButton)
                .show();
    }
}
