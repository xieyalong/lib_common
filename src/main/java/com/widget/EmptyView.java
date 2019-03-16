package com.widget;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CAPP;
import com.lib_common.R;

/**
 * queryRecordAdapter.setEmptyView(new EmptyView().getView());
 */
public class EmptyView {
    LinearLayout mEmptyView;
    TextView tv_empty;
    public  EmptyView(){
        mEmptyView=(LinearLayout)View.inflate(CAPP.getInstanc().getApplicationContext(), R.layout.view_empty,null);
        tv_empty=mEmptyView.findViewById(R.id.tv_empty);
    }
    public  LinearLayout getView(){
        return  mEmptyView;
    }
    public  void setEmptyViewClick(View.OnClickListener onClickListener){
        mEmptyView.setOnClickListener(onClickListener);
    }

    public TextView getTv_empty() {
        return tv_empty;
    }
}
