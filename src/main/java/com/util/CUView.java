package com.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CAPP;
import com.apkfuns.logutils.LogUtils;
import com.lib_common.R;
import com.widget.MyLinearLayoutManager;

/**
 * view属性处理
 */
public class CUView {
    private CUView(){}
    private  static CUView intance;
    public  static CUView getInstance(){
        if (null==intance){
            intance=new CUView();
        }
        return  intance;
    }
    public  void setImageDrawable(ImageView iv, int drawableId){
        if (null==iv){
            return;
        }
        iv.setImageDrawable(CUActivity.getIntanc().getActivity().getDrawable(drawableId));
    }

    public  void setCompoundDrawablesLeft(TextView tv,int drawableId){
        if (null==tv){
            return;
        }
        Drawable dra = CAPP.getInstanc().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(dra,null,null, null);
    }
    public  void setCompoundDrawablesTop(TextView tv,int drawableId){
        if (null==tv){
            return;
        }
        Drawable dra = CAPP.getInstanc().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(null,dra,null, null);
    }
    public  void setCompoundDrawablesRight(TextView tv,int drawableId){
        if (null==tv){
            return;
        }
        if (0==drawableId){
//            Drawable dra = CommonAPP.getInstanc().getResources().getDrawable(drawableId);
//            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
            tv.setCompoundDrawables(null,null,null, null);
        }else{
            Drawable dra = CAPP.getInstanc().getResources().getDrawable(drawableId);
            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
            tv.setCompoundDrawables(null,null,dra, null);
        }
    }
    public  void setCompoundDrawablesBottom(TextView tv,int drawableId){
        if (null==tv){
            return;
        }
        Drawable dra = CAPP.getInstanc().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(null,null,null, dra);
    }
    public  void setTextColor(TextView tv,int color){
        if (null==tv){
            return;
        }
        tv.setTextColor(CAPP.getInstanc().getResources().getColor(color));
    }
    public  void setBackgroundDrawable(View v,int drawableId){
        v.setBackground(CAPP.getInstanc().getResources().getDrawable(drawableId));
    }

    /**
     * 纵向滑动
     * @param rv_list
     * @return
     */
    public  LinearLayoutManager setLayoutManagerVERTICAL(RecyclerView rv_list){
        LinearLayoutManager layoutManager=new LinearLayoutManager(CUActivity.getIntanc().getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(layoutManager);
        return  layoutManager;
    }

    /**
     * 横向滑动
     * @param rv_list
     * @return
     */
    public  LinearLayoutManager setLlayoutManagerHORIZONTAL(RecyclerView rv_list){
        LinearLayoutManager layoutManager=new LinearLayoutManager(CUActivity.getIntanc().getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list.setLayoutManager(layoutManager);
        return  layoutManager;
    }

    public  GridLayoutManager setGridLayoutManager(RecyclerView rv_list,int spanCount){
        GridLayoutManager gm=new GridLayoutManager(CUActivity.getIntanc().getActivity(),spanCount);
        rv_list.setLayoutManager(gm);
        return  gm;
    }
    public  GridLayoutManager setGridLayoutManager(RecyclerView rv_list,int spanCount,int orientation){
        GridLayoutManager gm=new GridLayoutManager(CUActivity.getIntanc().getActivity(),spanCount);
        gm.setOrientation(orientation);
        rv_list.setLayoutManager(gm);
        return  gm;
    }

    /**
     * ScrollView和RecyclerView嵌套
     * @param rv_list
     * @param scrollEnabled  false=禁止滑动
     * @return
     */
    @Deprecated
    public  MyLinearLayoutManager setMyLinearLayoutManager(RecyclerView rv_list,boolean scrollEnabled){
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(CUActivity.getIntanc().getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setScrollEnabled(scrollEnabled);
        rv_list.setLayoutManager(layoutManager);
        return  layoutManager;
    }

    public  void setTextAppearance(TextView v,int styleId){
        v.setTextAppearance(CUActivity.getIntanc().getActivity(), styleId);
    }

    /**
     * 动态设置ListView的高度
     * 当ScrollView中包含ListView的时候，必须要手动的给设置ListView的高度
     * 否则的话，listView只会显示一行数据
     * 注意：要在adapter.notifyDataSetChanged();前
     * @param listView
     */
    public   void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    /**
     * 设置视图的宽高
     * @param view
     */
    public  void widthHigh(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    private  long lastClickTime;

    /**
     * 防止多次点击
     *   if (Utils.isFastDoubleClick()) {
     return;
     }else{
     //弹出Toast或者Dialog
     }
     * @return
     */
    public  boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public  void setTextChangedListener(TextView tv,
                                        final BeforeTextChanged beforeTextChanged,
                                        final TextChanged textChanged,
                                        final AfterTextChanged afterTextChanged){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (null!=beforeTextChanged){
                    beforeTextChanged.beforeTextChanged(s,start,count,after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textChanged!=null){
                    textChanged.onTextChanged(s,start,before,count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null!=afterTextChanged){
                    afterTextChanged.afterTextChanged(s);
                }
            }
        });
    }
    public  interface  AfterTextChanged{
        void afterTextChanged(Editable s);
    }
    public  interface  BeforeTextChanged{
        void beforeTextChanged(CharSequence s, int start, int count, int after);
    }
    public  interface  TextChanged{
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
    /**
     * 设置TabLayout中的字体是否要加粗
     * @param tabLayout
     * @param isBold
     * @param position
     */
    public  void setTabLayoutTabTextStyle(TabLayout tabLayout, boolean isBold, int position) {
        try {
            LinearLayout ly = (LinearLayout) tabLayout.getChildAt(position);
            LinearLayout tabView = (LinearLayout) ly.getChildAt(position);
            if (null != tabView && tabView.getChildCount() > 0) {
                View view = tabView.getChildAt(0);
                if (null != view && view instanceof AppCompatTextView) {
                    if (isBold) {
                        ((AppCompatTextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        ((AppCompatTextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  AppCompatTextView getTabLayoutTab(TabLayout tabLayout, int position){
        try {
//            for (int i = 0; i < tabLayout.getChildCount(); i++) {
//                LogUtils.i(">]1="+tabLayout.getTabAt(i));
//                ()tabLayout.getTabAt(i)
//
//            }
//            Object object=tabLayout.getChildAt(position);
            LinearLayout ly = (LinearLayout) tabLayout.getChildAt(position);
            LinearLayout tabView = (LinearLayout) ly.getChildAt(position);
            if (null != tabView && tabView.getChildCount() > 0) {
                View view = tabView.getChildAt(0);
                if (null != view && view instanceof AppCompatTextView) {
                    return  ((AppCompatTextView) view);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取屏幕的宽度
    public  int getScreenWidth(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    //获取屏幕的高度
    public  int getScreenHeight(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
    //获取屏幕的密度
    public  float getScreenDensity(){
        DisplayMetrics displayMetrics= CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics();
        return displayMetrics.density;
    }
    /**
     * 获取当前屏幕截图，包含状态栏
     * @return
     */
    public  Bitmap snapShotWithStatusBar(){
        View view = CUActivity.getIntanc().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width =getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * @return
     */
    public Bitmap snapShotWithoutStatusBar() {
        View view = CUActivity.getIntanc().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        CUActivity.getIntanc().getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }
    /**
     * 得到设备的密度
     */
    public float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    /**
     * dp转px
     * @param dpVal
     * @return
     */
    public  int dp2px(float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxVal
     * @return
     */
    public  float px2dp(float pxVal){
        final float scale = CAPP.getInstanc().getApplicationContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public  int sp2px(float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, CAPP.getInstanc().getResources().getDisplayMetrics());
    }
    /**
     * 把密度转换为像素
     */
    public int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }


    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp( float pxValue) {
        final float fontScale = CAPP.getInstanc().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 设置linearLayout下边距  父布局为LinearLayout
     * @param linearLayout
     * @param dpVal
     */
    public  void setLinearLayoutParams_bottomMargin(final LinearLayout linearLayout,final int dpVal){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.bottomMargin=dp2px(dpVal);
        linearLayout.setLayoutParams(params);
//            }
//        });
    }
    public  void setLinearLayoutParams_bottomMargin(final ViewGroup viewGroup, final int dpVal){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) viewGroup.getLayoutParams();
        params.bottomMargin=dp2px(dpVal);
        viewGroup.setLayoutParams(params);
//            }
//        });
    }
    /**
     * 设置linearLayout下边距  父布局为LinearLayout
     * @param linearLayout
     * @param dpVal
     */
    public  void setLinearLayoutParams_bottomMargin(final RelativeLayout linearLayout,final int dpVal){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.bottomMargin=dp2px(dpVal);
        linearLayout.setLayoutParams(params);
//            }
//        });
    }
    /**
     * 父布局为RelativeLayout
     * @param linearLayout
     * @param dpVal
     */
    public  void setRelativeLayoutParams_bottomMargin(final LinearLayout linearLayout,final int dpVal){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        params.bottomMargin=dp2px(dpVal);
        linearLayout.setLayoutParams(params);
//            }
//        });
    }

    public  void setRelativeLayoutParams_bottomMargin(final ViewGroup linearLayout,final int dpVal){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        params.bottomMargin=dp2px(dpVal);
        linearLayout.setLayoutParams(params);
//            }
//        });
    }
    public  void setRelativeLayoutParams_padding(final ViewGroup linearLayout, int dp_left, int dp_top, int dp_right, int dp_bottom){
//        linearLayout.post(new Runnable() {
//            @Override
//            public void run() {
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        linearLayout.setPadding(dp2px(dp_left),dp2px(dp_top),dp2px(dp_right),dp2px(dp_bottom));
        linearLayout.setLayoutParams(params);
//            }
//        });
    }
    public  void setRelativeLayoutParams_height(final View v,final int dpVal){
//        v.post(new Runnable() {
//            @Override
//            public void run() {
                RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) v.getLayoutParams();
                params.height=dp2px(dpVal);
                v.setLayoutParams(params);
//            }
//        });
    }

    /**
     * 设置文本不可见 密码不可见
     * @param textView
     */
    public void  setTransformationMethod(TextView textView){
        textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

}
