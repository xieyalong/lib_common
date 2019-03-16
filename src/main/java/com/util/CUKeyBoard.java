package com.util;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.CAPP;
import com.util.Utilc.EditorActionListener;
import com.util.Utilc.FocusChange;

/**
 * 键盘管理
 */
public class CUKeyBoard {
    private CUKeyBoard(){}
    private  static CUKeyBoard intance;
    public  static CUKeyBoard getInstance(){
        if (null==intance){
            intance=new CUKeyBoard();
        }
        return  intance;
    }
    /**
     * 打开软键盘
     */
    public  void openKeybord(EditText mEditText){
        InputMethodManager imm = (InputMethodManager) CAPP.getInstanc()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     */
    public  void closeKeybord(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) CAPP.getInstanc()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 按键按键监听
     * @param editText
     * @param listener
     */
    public  void setOnEditorActionListener(EditText editText,final EditorActionListener listener){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if(actionId == EditorInfo.IME_ACTION_GO){
                    listener.actionGo(v,actionId,event);
                    return true;
                }else if (actionId == EditorInfo.IME_ACTION_SEARCH){//搜索键
                    listener.actionSearch(v,actionId,event);
                }
                return false;
            }
        });
    }
    public  void setFocusChange(EditText editText, final FocusChange focusChange){
        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    focusChange.focusTrue(v);
//                    LogUtils.i(">]得到焦点");
                } else {
                    focusChange.focusFalse(v);
//                    LogUtils.i(">]失去焦点");
                }
            }
        });
    }
    /**
     * 隐藏键盘
     */
    public void hideInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = activity.getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    //----监听软键盘弹出和隐藏-------------------------------------------------------------
    private View rootView;//activity的根视图
    int rootViewVisibleHeight;//纪录根视图的显示高度
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public CUKeyBoard(Activity activity) {
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

    /**
     * 动画操作 在onCreate里面调用
     * @param activity
     * @param rootView activity根目录
     * @param dpValue
     */
    public  void setListener_translationY(Activity activity, final View rootView , final int dpValue) {
        CUKeyBoard softKeyBoardListener = new CUKeyBoard(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(new OnSoftKeyBoardChangeListener() {
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
    public  void setListener_translationY(Activity activity, final int rootViewId , final int dpValue) {
        final View rootView=activity.findViewById(rootViewId);
        CUKeyBoard softKeyBoardListener = new CUKeyBoard(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(new OnSoftKeyBoardChangeListener() {
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
    public  void scrollToViewBottom(final ScrollView scrollView, final View view) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                scrollView.scrollTo(0, view.getMeasuredHeight() - scrollView.getHeight());
            }
        }, 100);
    }

}
