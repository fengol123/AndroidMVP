package com.fdb.mvpdemo.widget.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fdb.baselibrary.base.BaseApplication;

/**
 * Created by xieguofeng on 2018/7/16
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    protected final View mRootView; //根布局
    protected T mBean;              //holder的数据
    protected int mPosition;        //数据在列表中的下标
    private OnItemClickListener<T> mOnItemClickListener;    //根布局的点击监听

    public BaseHolder(View itemView) {
        this(null, itemView);
    }

    public BaseHolder(Context context, View itemView) {
        super(itemView);
        if (context == null) {
            context = BaseApplication.getApp();
        }
        mRootView = View.inflate(context, getLayoutId(), null);
        ViewGroup viewGroup = (ViewGroup) itemView;
        viewGroup.addView(mRootView);

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mBean, mPosition);
                }
            }
        });
    }

    /**
     * 获取holder的布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 给holder设置数据,以及刷新页面
     *
     * @param bean     数据
     * @param position 下标
     */
    public void setData(T bean, int position) {
        mBean = bean;
        mPosition = position;
        onSetData();
    }

    /**
     * 获取根布局
     *
     * @return
     */
    public View getRootView() {
        return mRootView;
    }

    /**
     * 刷新页面
     */
    protected abstract void onSetData();

    /**
     * 设置布局的点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T data, int position);
    }
}










