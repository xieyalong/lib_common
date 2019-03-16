package com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.lib_common.R;
import com.util.CUAPP;
import com.util.CUStatusBar;
import com.widget.HeadLayout;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public abstract class CommonActivity extends SupportActivity implements HeadLayout.HeadLayoutOnClickListener,View.OnClickListener{
    private  int click_num;
    private HeadLayout headLayout;
    protected Context mContext;
    protected Activity mActivity;
    public  boolean isBack=true;
    //    SupportActivityDelegate mDelegate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
            CUStatusBar.getInstance().setStatusBar(this);
//            mDelegate = new SupportActivityDelegate(this);
//            mDelegate.onCreate(savedInstanceState);
            if (null!=getSupportActionBar()){
                getSupportActionBar().hide();
            }

            mContext=this;
            mActivity=this;
            if (setContentViewLayout()>0){
                setContentView(setContentViewLayout());
                setHeadLayout();
                initViewDataBefore();
                initViews();
                initViewDataAfter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public abstract int setContentViewLayout();
    public  abstract  void initViews();
    public  void initViewDataBefore(){}
    public  void initViewDataAfter(){}
    public  HeadLayout getHeadLayout(){
        return  headLayout;
    }
    public  void setHeadLayout(){
        try {
            headLayout=findView(R.id.hl_layout);
            if (null!=headLayout){
                headLayout.setHeadLayoutOnClickListener(this);
                headLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (click_num==2){
                            click_num=0;
                            print();
                        }else{
                            click_num++;
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 封装的findViewByID方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(@IdRes int id) {
        return (T) super.findViewById(id);
    }
    protected void onClickBack(int i){
        if (isBack){
            if (0==i){
                onBackPressed();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onClickBack(0);
                return  true;
        }
        return  super.onKeyDown(keyCode, event);
    }
    //回退
    @Override
    public void leftOnClick() {
        onClickBack(0);
    }
    //图片
    @Override
    public void rightIv2OnClick() {

    }
    //文字
    @Override
    public void rightOnClick() {

    }
    @Override
    public void rightIv1OnClick() {

    }

    @Override
    public void titleIvOnClick() {

    }

    @Override
    public void titleTv2OnClick() {

    }

    @Override
    public void onClick(View v) {

    }
    public void print(){
        System.out.println(">]activity="+this.getClass().getName());
    }
//    @Override
//    protected void onDestroy() {
//        if (null!=mDelegate){
//            mDelegate.onDestroy();
//        }
//        super.onDestroy();
//    }
//    @Override
//    public SupportActivityDelegate getSupportDelegate() {
//        return mDelegate;
//    }
//
//    /**
//     * Perform some extra transactions.
//     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
//     */
//    @Override
//    public ExtraTransaction extraTransaction() {
//        return mDelegate.extraTransaction();
//    }
//
//
//
//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        mDelegate.onPostCreate(savedInstanceState);
//    }
//
//
//
//    /**
//     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
//    }
//
//    /**
//     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
//     */
//    @Override
//    final public void onBackPressed() {
//        mDelegate.onBackPressed();
//    }
//
//    /**
//     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
//     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
//     */
//    @Override
//    public void onBackPressedSupport() {
//        mDelegate.onBackPressedSupport();
//    }
//
//    /**
//     * 获取设置的全局动画 copy
//     *
//     * @return FragmentAnimator
//     */
//    @Override
//    public FragmentAnimator getFragmentAnimator() {
//        return mDelegate.getFragmentAnimator();
//    }
//
//    /**
//     * Set all fragments animation.
//     * 设置Fragment内的全局动画
//     */
//    @Override
//    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
//        mDelegate.setFragmentAnimator(fragmentAnimator);
//    }
//
//    /**
//     * Set all fragments animation.
//     * 构建Fragment转场动画
//     * <p/>
//     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
//     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
//     *
//     * @return FragmentAnimator对象
//     */
//    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        return onCreateFragmentAnimator();
//    }
//
//    @Override
//    public void post(Runnable runnable) {
//        post(runnable);
//    }
//
//    /****************************************以下为可选方法(Optional methods)******************************************************/
//
//    /**
//     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
//     *
//     * @param containerId 容器id
//     * @param toFragment  目标Fragment
//     */
//    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
//        loadRootFragment(containerId, toFragment);
//    }
//
//    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnimation) {
//        loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation);
//    }
//
//    /**
//     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
//     */
//    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
//        loadMultipleRootFragment(containerId, showPosition, toFragments);
//    }
//
//    /**
//     * show一个Fragment,hide其他同栈所有Fragment
//     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
//     * <p>
//     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
//     *
//     * @param showFragment 需要show的Fragment
//     */
//    public void showHideFragment(ISupportFragment showFragment) {
//        showHideFragment(showFragment);
//    }
//
//    /**
//     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
//     */
//    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
//        showHideFragment(showFragment, hideFragment);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#start(ISupportFragment)}.
//     */
//    public void start(ISupportFragment toFragment) {
//        mDelegate.start(toFragment);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#start(ISupportFragment, int)}.
//     *
//     * @param launchMode Similar to Activity's LaunchMode.
//     */
//    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
//        mDelegate.start(toFragment, launchMode);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#startForResult(ISupportFragment, int)}.
//     * Launch an fragment for which you would like a result when it poped.
//     */
//    public void startForResult(ISupportFragment toFragment, int requestCode) {
//        mDelegate.startForResult(toFragment, requestCode);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#startWithPop(ISupportFragment)}.
//     * Start the target Fragment and pop itself
//     */
//    public void startWithPop(ISupportFragment toFragment) {
//        mDelegate.startWithPop(toFragment);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#startWithPopTo(ISupportFragment, Class, boolean)}.
//     *
//     * @see #popTo(Class, boolean)
//     * +
//     * @see #start(ISupportFragment)
//     */
//    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
//        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
//    }
//
//    /**
//     * It is recommended to use {@link SupportFragment#replaceFragment(ISupportFragment, boolean)}.
//     */
//    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
//        mDelegate.replaceFragment(toFragment, addToBackStack);
//    }
//
//    /**
//     * Pop the fragment.
//     */
//    public void pop() {
//        mDelegate.pop();
//    }
//
//    /**
//     * 从管理器的片段中弹出最后一个片段转换
//     * back stack.
//     * <p>
//     * 出栈到目标fragment
//     *
//     * @param targetFragmentClass   目标fragment
//     * @param includeTargetFragment 是否包含该fragment
//     */
//    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
//        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
//    }
//
//    /**
//     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
//     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
//     */
//    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
//        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
//    }
//
//    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
//        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
//    }
//
//    /**
//     * 当Fragment根布局 没有 设定background属性时,
//     * Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
//     * 可以通过该方法改变其内所有Fragment的默认背景。
//     */
//    public void setDefaultFragmentBackground(@DrawableRes int backgroundRes) {
//        mDelegate.setDefaultFragmentBackground(backgroundRes);
//    }
//
//    /**
//     * 得到位于栈顶Fragment
//     */
//    public ISupportFragment getTopFragment() {
//        return SupportHelper.getTopFragment(getSupportFragmentManager());
//    }
//
//    /**
//     * 获取栈内的fragment对象
//     */
//    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
//        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
//    }
}
