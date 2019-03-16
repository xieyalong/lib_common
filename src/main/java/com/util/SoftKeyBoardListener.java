package com.util;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;


/**
 * 监听软键盘打开关起
 */
public class SoftKeyBoardListener {
    private View rootView;//activity的根视图
    int rootViewVisibleHeight;//纪录根视图的显示高度
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public SoftKeyBoardListener(Activity activity) {
        //获取activity的根视图
        rootView = activity.getWindow().getDecorView();

        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);

                int visibleHeight = r.height();
                System.out.println("" + visibleHeight);
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                //根视图显示高度变小超过200，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度变大超过200，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }

    /**
     * 动画操作 在onCreate里面调用
     * @param activity
     * @param rootView activity根目录
     * @param dpValue
     */
    public static void setListener_translationY(Activity activity,final View rootView ,final int dpValue) {
        SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                ObjectAnimator.ofFloat(rootView, "translationY", 0, -CUDensity.getInstance().dp2px(dpValue)).setDuration(100).start();
            }

            @Override
            public void keyBoardHide(int height) {
                ObjectAnimator.ofFloat(rootView, "translationY", -CUDensity.getInstance().dp2px(dpValue), 0).setDuration(100).start();
            }
        });
    }
    /**
     * 将滚动条滚动到指定的view底部
     * @param scrollView
     * @param view
     */
    public static void scrollToViewBottom(final ScrollView scrollView, final View view) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                scrollView.scrollTo(0, view.getMeasuredHeight() - scrollView.getHeight());
            }
        }, 100);
    }
}
