package com.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lib_common.R;

public abstract class StatusBarBaseActivity extends AppCompatActivity implements View.OnClickListener{
    View mv_baseStatusBar,mv_contentView;
    Toolbar toolBar;
    TextView tv_base_title,tv_base_rigtht;
    ImageView iv_base_back,iv_base_rigtht;
    LinearLayout layout_toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (setContentViewLayout()>0){
                setContentView(setContentViewLayout());
                initViewDataBefore();
                initViews(mv_contentView);
                initViewDataAfter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        try {
            // 设置透明状态栏
            setStatusBar(this);
            mv_baseStatusBar =LayoutInflater.from(this).inflate(R.layout.activity_status_bar_base, null);
            if (initLayoutToolbar()==0){//默认
                toolBar=mv_baseStatusBar.findViewById(R.id.tool_bar);
                tv_base_title=mv_baseStatusBar.findViewById(R.id.tv_base_title);
                tv_base_title.setOnClickListener(this);
                iv_base_back=mv_baseStatusBar.findViewById(R.id.iv_base_back);
                iv_base_back.setOnClickListener(this);
                tv_base_rigtht=mv_baseStatusBar.findViewById(R.id.tv_base_rigtht);
                tv_base_rigtht.setOnClickListener(this);
                iv_base_rigtht=mv_baseStatusBar.findViewById(R.id.iv_base_rigtht);
                iv_base_rigtht.setOnClickListener(this);
            }else{//自定义title栏
                layout_toolbar=mv_baseStatusBar.findViewById(R.id.layout_toolbar);
                layout_toolbar.removeAllViews();
                layout_toolbar.addView(View.inflate(this,initLayoutToolbar(), null));
            }
            mv_contentView =View.inflate(this,layoutResID, null);
            // content
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mv_contentView.setLayoutParams(params);
            RelativeLayout mContainer = (RelativeLayout) mv_baseStatusBar.findViewById(R.id.container);
            mContainer.addView(mv_contentView);
            getWindow().setContentView(mv_baseStatusBar);
//            //设置标题栏
            setSupportActionBar(toolBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置状态栏颜色为透明色，这样能保证状态栏为沉浸式状态
     * 根据SDK >= 21时，标题栏高度设为statusBarHeight(25dp)+titlBarHeight(48dp)
     * 若SDK < 21,标题栏高度直接为titlBarHeight
     */
    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.titlebar_main_color));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    public abstract int setContentViewLayout();
    public  abstract  void initViews(View contentView);
    public  void initViewDataBefore(){}
    public  void initViewDataAfter(){}

    /**
     *   自定义titlebar
     * @return
     */
    public  int initLayoutToolbar(){
        return 0;
    }
    /**
     * 设置标题
     * @param text
     */
    public void setTitle(CharSequence text) {
        if (null==tv_base_title)return;
        tv_base_title.setText(text);
    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_base_back) {
            onClickBack(0);
        } else if (i == R.id.tv_base_rigtht) {
            onClick_tvRigtht();

        } else if (i == R.id.iv_base_rigtht) {
            onClick_ivRigtht();

        } else if (i == R.id.tv_base_title) {
            onClick_tvTitle();

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onClickBack(0);
                break;
        }
        return false;
    }
    public void onClickBack(int i){
        if (0==i){
            onBackPressed();
        }
    }

    public void onClick_tvRigtht(){
        Toast.makeText(this,"tv",Toast.LENGTH_SHORT).show();
    }
    public void onClick_ivRigtht(){
        Toast.makeText(this,"iv",Toast.LENGTH_SHORT).show();
    }
    public void onClick_tvTitle(){
        Toast.makeText(this,"title",Toast.LENGTH_SHORT).show();
    }
    public void tvRigthtVis(int visibility,String str){
        if (null==tv_base_rigtht)return;
        tv_base_rigtht.setVisibility(visibility);
        tv_base_rigtht.setText(str);
    }
    public void ivRigthtVis(int visibility,int mipmap){
        if (null==iv_base_rigtht)return;
        iv_base_rigtht.setVisibility(visibility);
        if (mipmap>0){
            iv_base_rigtht.setImageResource(mipmap);
        }
    }
    public void tvTitleVis(int visibility){
        if (null==tv_base_title)return;
        tv_base_title.setVisibility(visibility);
    }
    public void ivBackVis(int visibility){
        if (null==iv_base_back)return;
        iv_base_back.setVisibility(visibility);
    }
}
