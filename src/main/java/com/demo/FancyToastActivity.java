package com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lib_common.R;
import com.util.CUToast;

public class FancyToastActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy_toast);

    }

    @Override
    public void onClick(View v) {
       if (v.getId()==R.id.tv1){
           CUToast.getInstance().showToast("默认");
       }else   if (v.getId()==R.id.tv2){
           CUToast.getInstance().showSuccessToast("成功");
       }else  if (v.getId()==R.id.tv3){
            CUToast.getInstance().showErrorToast("错误");
        }else  if (v.getId()==R.id.tv4){
           CUToast.getInstance().showWarningToast("警告");
       }
    }
}
