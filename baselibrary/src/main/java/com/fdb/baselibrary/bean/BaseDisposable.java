package com.fdb.baselibrary.bean;

import io.reactivex.disposables.Disposable;

/**
 * Desc 可监听的Disposable
 * Author feng
 * Date   2020/5/20.
 */
public class BaseDisposable{
    private Disposable mDisposable;
    private OnDisposeListener mListener;

    public BaseDisposable(Disposable disposable, OnDisposeListener listener) {
        mDisposable = disposable;
        mListener = listener;
    }

    public void dispose(){
        mDisposable.dispose();
        if(mListener != null){
            mListener.onDispose();
        }
    }

    public interface OnDisposeListener{
        void onDispose();
    }
}
