package com.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * ScrollView和RecyclerView嵌套
 *      MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(mContext);
 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
 layoutManager.setScrollEnabled(false);
 rv_list.setLayoutManager(layoutManager);
 */
public class MyLinearLayoutManager extends LinearLayoutManager {
    boolean isScrollEnabled;
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
