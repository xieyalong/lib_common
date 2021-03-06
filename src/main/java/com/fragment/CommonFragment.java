package com.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.activity.CommonActivity;
import com.apkfuns.logutils.LogUtils;
import com.lib_common.R;
import com.widget.HeadLayout;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by xieyalong
 */

public abstract class CommonFragment extends SupportFragment implements HeadLayout.HeadLayoutOnClickListener,View.OnClickListener{
    private  int click_num;
    protected Context mContext;
    private HeadLayout headLayout;
    private View mView;
    public CommonActivity mActivity;
//    SupportFragmentDelegate mDelegate;
//    protected FragmentActivity _mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity();
        mActivity=(CommonActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mDelegate=new SupportFragmentDelegate(this);
//        mDelegate.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            mView = inflater.inflate(setContentViewLayout(), container, false);
            setHeadLayout();
            initViewDataBefore();
            initViews(mView);
            initViewDataAfter();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        动态改动 当前Fragment的动画
//        setFragmentAnimator(fragmentAnimator);
        return mView;
    }
    public  abstract  int setContentViewLayout();
    public abstract void initViews(View mView);
    public  void initViewDataBefore(){}
    public  void initViewDataAfter(){}
    public  HeadLayout getHeadLayout(){
        return  headLayout;
    }
    public  void setHeadLayout(){
        try {
            headLayout=mView.findViewById(R.id.hl_layout);
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

    protected <T extends View> T findView(@IdRes int id) {
        if (mView!=null){
            return (T) mView.findViewById(id);
        }
        return  null;

    }




    //回退
    @Override
    public void leftOnClick() {
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
        System.out.println(">]fragment="+this.getClass().getName());
    }

//
//    @Override
//    public SupportFragmentDelegate getSupportDelegate() {
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
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mDelegate.onAttach(activity);
//        _mActivity = mDelegate.getActivity();
//    }
//
//
//
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        return mDelegate.onCreateAnimation(transit, enter, nextAnim);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mDelegate.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mDelegate.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mDelegate.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mDelegate.onPause();
//    }
//
//    @Override
//    public void onDestroyView() {
//        mDelegate.onDestroyView();
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        if (null!=mDelegate){
//            mDelegate.onDestroy();
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        mDelegate.onHiddenChanged(hidden);
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        mDelegate.setUserVisibleHint(isVisibleToUser);
//    }
//
//    /**
//     * Causes the Runnable r to be added to the action queue.
//     * <p>
//     * The runnable will be run after all the previous action has been run.
//     * <p>
//     * 前面的事务全部执行后 执行该Action
//     *
//     * @deprecated Use {@link #post(Runnable)} instead.
//     */
//    @Deprecated
//    @Override
//    public void enqueueAction(Runnable runnable) {
//        mDelegate.enqueueAction(runnable);
//    }
//
//    /**
//     * Causes the Runnable r to be added to the action queue.
//     * <p>
//     * The runnable will be run after all the previous action has been run.
//     * <p>
//     * 前面的事务全部执行后 执行该Action
//     */
//    @Override
//    public void post(Runnable runnable) {
//        mDelegate.post(runnable);
//    }
//
//    /**
//     * Called when the enter-animation end.
//     * 入栈动画 结束时,回调
//     */
//    @Override
//    public void onEnterAnimationEnd(Bundle savedInstanceState) {
//        mDelegate.onEnterAnimationEnd(savedInstanceState);
//    }
//
//
//    /**
//     * Lazy initial，Called when fragment is first called.
//     * <p>
//     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
//     */
//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        mDelegate.onLazyInitView(savedInstanceState);
//    }
//
//    /**
//     * Called when the fragment is visible.
//     * 当Fragment对用户可见时回调
//     * <p>
//     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
//     */
//    @Override
//    public void onSupportVisible() {
//        mDelegate.onSupportVisible();
//        LogUtils.i(">]className="+this.getClass().getName());
//    }
//
//    /**
//     * 当碎片无法识别时调用。
//     * <p>
//     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
//     */
//    @Override
//    public void onSupportInvisible() {
//        mDelegate.onSupportInvisible();
//    }
//
//    /**
//     * 如果片段已经支持，则返回true。
//     */
//    @Override
//    final public boolean isSupportVisible() {
//        return mDelegate.isSupportVisible();
//    }
//
//    /**
//     * Set fragment animation with a higher priority than the ISupportActivity
//     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
//     */
//    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        return mDelegate.onCreateFragmentAnimator();
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
//     * 设置Fragment内的全局动画
//     */
//    @Override
//    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
//        mDelegate.setFragmentAnimator(fragmentAnimator);
//    }
//
//    /**
//     * 按返回键触发,前提是SupportActivity的onBackPressed()方法能被调用
//     *
//     * @return false则继续向上传递, true则消费掉该事件
//     */
//    @Override
//    public boolean onBackPressedSupport() {
//        return mDelegate.onBackPressedSupport();
//    }
//
//    /**
//     * 类似 {@link Activity#setResult(int, Intent)}
//     * <p>
//     * Similar to {@link Activity#setResult(int, Intent)}
//     *
//     * @see #startForResult(ISupportFragment, int)
//     */
//    @Override
//    public void setFragmentResult(int resultCode, Bundle bundle) {
//        mDelegate.setFragmentResult(resultCode, bundle);
//    }
//
//    /**
//     * 类似  {@link Activity#onActivityResult(int, int, Intent)}
//     * <p>
//     * Similar to {@link Activity#onActivityResult(int, int, Intent)}
//     *
//     * @see #startForResult(ISupportFragment, int)
//     */
//    @Override
//    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
//        mDelegate.onFragmentResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
//     * 类似 {@link Activity#onNewIntent(Intent)}
//     * <p>
//     * Similar to {@link Activity#onNewIntent(Intent)}
//     *
//     * @param args putNewBundle(Bundle newBundle)
//     * @see #start(ISupportFragment, int)
//     */
//    @Override
//    public void onNewBundle(Bundle args) {
//        mDelegate.onNewBundle(args);
//    }
//
//    /**
//     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
//     *
//     * @see #start(ISupportFragment, int)
//     */
//    @Override
//    public void putNewBundle(Bundle newBundle) {
//        mDelegate.putNewBundle(newBundle);
//    }
//
//
//    /****************************************以下为可选方法(Optional methods)******************************************************/
//
//    /**
//     * 隐藏软键盘
//     */
//    protected void hideSoftInput() {
//        mDelegate.hideSoftInput();
//    }
//
//    /**
//     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
//     */
//    protected void showSoftInput(final View view) {
//        mDelegate.showSoftInput(view);
//    }
//
//    /**
//     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
//     *
//     * @param containerId 容器id
//     * @param toFragment  目标Fragment
//     */
//    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
//        mDelegate.loadRootFragment(containerId, toFragment);
//    }
//
//    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
//        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
//    }
//
//    /**
//     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
//     */
//    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
//        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
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
//        mDelegate.showHideFragment(showFragment);
//    }
//
//    /**
//     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
//     */
//    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
//        mDelegate.showHideFragment(showFragment, hideFragment);
//    }
//
//    public void start(ISupportFragment toFragment) {
//        mDelegate.start(toFragment);
//    }
//
//    /**
//     * @param launchMode Similar to Activity's LaunchMode.
//     */
//    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
//        mDelegate.start(toFragment, launchMode);
//    }
//
//    /**
//     * Launch an fragment for which you would like a result when it poped.
//     */
//    public void startForResult(ISupportFragment toFragment, int requestCode) {
//        mDelegate.startForResult(toFragment, requestCode);
//    }
//
//    /**
//     * Start the target Fragment and pop itself
//     */
//    public void startWithPop(ISupportFragment toFragment) {
//        mDelegate.startWithPop(toFragment);
//    }
//
//    /**
//     * @see #popTo(Class, boolean)
//     * +
//     * @see #start(ISupportFragment)
//     */
//    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
//        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
//    }
//
//
//    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
//        mDelegate.replaceFragment(toFragment, addToBackStack);
//    }
//
//    public void pop() {
//        mDelegate.pop();
//    }
//
//    /**
//     * Pop the child fragment.
//     */
//    public void popChild() {
//        mDelegate.popChild();
//    }
//
//    /**
//     * Pop the last fragment transition from the manager's fragment
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
//    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment) {
//        mDelegate.popToChild(targetFragmentClass, includeTargetFragment);
//    }
//
//    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
//        mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
//    }
//
//    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
//        mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
//    }
//
//    /**
//     * 得到位于栈顶Fragment
//     */
//    public ISupportFragment getTopFragment() {
//        return SupportHelper.getTopFragment(getFragmentManager());
//    }
//
//    public ISupportFragment getTopChildFragment() {
//        return SupportHelper.getTopFragment(getChildFragmentManager());
//    }
//
//    /**
//     * @return 位于当前Fragment的前一个Fragment
//     */
//    public ISupportFragment getPreFragment() {
//        return SupportHelper.getPreFragment(this);
//    }
//
//    /**
//     * 获取栈内的fragment对象
//     */
//    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
//        return SupportHelper.findFragment(getFragmentManager(), fragmentClass);
//    }
//
//    /**
//     * 获取栈内的fragment对象
//     */
//    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
//        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
//    }
}
