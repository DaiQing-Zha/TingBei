package com.jxnu.zha.qinglibrary.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jxnu.zha.qinglibrary.R;

/**
 * Created by DaiQing.Zha on 2017/3/16.
 * email:13767191284@163.com
 * description:
 */
public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTouchSlop;
    /**
     * listView实例
     */
    private ListView mListView;
    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    /**
     * 是否完成分页加载
     */
    private boolean isCompleteLoad = false;

    public RefreshLayout(Context context) {
        super(context);
    }
    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.layout_core_listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (canLoad()) loadData();
    }

    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(this);
            }
        }
    }

    /**
     * 是否可以加载更多数据
     * @return
     */
    private boolean canLoad(){
        if (isBottom() && !isLoading && isPullUp()) return true;
        return false;
    }

    /**
     * 是否拉到底部
     * @return
     */
    private boolean isBottom(){
        if (mListView != null && mListView.getAdapter() != null){
            if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1)
                return true;
        }
        return false;
    }
    /**
     * 判断是否是上拉
     * @return
     */
    private boolean isPullUp(){
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 数据加载
     */
    private void loadData(){
        if(isCompleteLoad){   //完成加载，不需要再进行分页加载数据
            return;
        }
        if (mOnLoadListener != null) {
            // 设置状态
            setLoading(true);
            mOnLoadListener.onPageLoad();
        }
    }

    /**
     * 是否显示分页加载
     * true:显示
     * false:不显示
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }
    /**
     * 加载更多监听器
     */
    public interface OnLoadListener {
        void onPageLoad();
    }
}
