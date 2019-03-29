package com.util;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.widget.TextView;

import com.zhl.cbdialog.CBDialogBuilder;

public class CUDialog {
    public static CUDialog instance;
    public  static CUDialog getInstance(){
        if (null==instance){
            instance=new CUDialog();
        }
        return instance;
    }
    public  Dialog  loadingDialogShow(Activity activity,String message){
        if (TextUtils.isEmpty(message)){
            message="正在加载请稍后...";
        }
      Dialog dialog=  new CBDialogBuilder(activity,CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.5f)
                .showCancelButton(true)
                .setMessage(message)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                    @Override
                    public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {
//                                dialogMsgTextView.setText("出错啦");
                    }
                })
                .setProgressTimeOutLimit(false)
//                        .setProgressStyleColorRes(new int[]{0xFF37474F,0xFF263238,0xFF21272B,0xFF80CBC4,0xFF009688,0xFFDE6262,0xFF0F519F})
                .create();
        dialog.show();
        return dialog;
    }
}
