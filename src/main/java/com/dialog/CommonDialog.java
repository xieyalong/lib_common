package com.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.lib_common.R;
import com.util.CUActivity;


public class CommonDialog extends Dialog implements View.OnClickListener{
    TextView tv_qr,tv_qx,tv_nr;
    public CommonDialog() {
        super(CUActivity.getIntanc().getActivity(), R.style.dialog1);
        try {
            setContentView(R.layout.dialog1);
            tv_nr=findViewById(R.id.tv_nr);
            tv_nr.setOnClickListener(this);
            tv_qx=findViewById(R.id.tv_qx);
            tv_qx.setOnClickListener(this);
            tv_qr=findViewById(R.id.tv_qr);
            tv_qr.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Confirm confirm;
    Cancel cancel;

    /**
     * 如果不需要取消，确认 传null
     * @param content
     * @param confirm
     * @param cancel
     */
    public  void showDialog(String content,Confirm confirm,Cancel cancel){
        try {
            if (isShowing()){
                return;
            }
            show();
            this.confirm=confirm;
            this.cancel=cancel;
            tv_nr.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void showDialog(boolean isancelable,String content,Confirm confirm,Cancel cancel){
        try {
            if (isShowing()){
                return;
            }
            show();
            setCancelable(isancelable);
            setCanceledOnTouchOutside(isancelable);
            this.confirm=confirm;
            this.cancel=cancel;
            tv_nr.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void showDialog(String content,boolean isconfirm,boolean iscancel,Confirm confirm,Cancel cancel){
        try {
            if (isShowing()){
                return;
            }
            show();
            if (isconfirm){
                tv_qr.setVisibility(View.VISIBLE);
            }else{
                tv_qr.setVisibility(View.GONE);
            }
            if (iscancel){
                tv_qx.setVisibility(View.VISIBLE);
            }else{
                tv_qx.setVisibility(View.GONE);
            }
            this.confirm=confirm;
            this.cancel=cancel;
            tv_nr.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void showDialog(boolean isancelable,String content,boolean isconfirm,boolean iscancel,Confirm confirm,Cancel cancel){
        try {
            if (isShowing()){
                return;
            }
            show();
            setCancelable(isancelable);
            setCanceledOnTouchOutside(isancelable);
            if (isconfirm){
                tv_qr.setVisibility(View.VISIBLE);
            }else{
                tv_qr.setVisibility(View.GONE);
            }
            if (iscancel){
                tv_qx.setVisibility(View.VISIBLE);
            }else{
                tv_qx.setVisibility(View.GONE);
            }
            this.confirm=confirm;
            this.cancel=cancel;
            tv_nr.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        try {
            int i = v.getId();
            if (i == R.id.tv_nr) {
            } else if (i == R.id.tv_qx) {
                dismiss();
                if (null != cancel) {
                    cancel.onClick();
                }

            } else if (i == R.id.tv_qr) {
                dismiss();
                if (confirm != null) {
                    confirm.onClick();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  interface Confirm{
        void onClick();
    }
    public  interface Cancel{
        void onClick();
    }
}
