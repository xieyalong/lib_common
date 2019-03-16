package com.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 *ScrollView 冲突使用
 */
public class CommonLinearLayoutManager extends LinearLayoutManager {
    boolean isScrollEnabled;
    public CommonLinearLayoutManager(Context context) {
        super(context);
    }

    public CommonLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CommonLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    /**
     * 是否支持滑动
     * @param flag
     */
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //isScrollEnabled：recyclerview是否支持滑动
        //super.canScrollVertically()：是否为竖直方向滚动
        return isScrollEnabled && super.canScrollVertically();
    }
}
