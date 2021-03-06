package com.fdb.baselibrary.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fdb.baselibrary.R;
import com.fdb.baselibrary.utils.L;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 加载中的loadingdialog
 */
public class LoadingDialog extends ProgressDialog {
    TextView mLoadingTextView;
    View mView;
    private CompositeSubscription mCompositeSubscription;

    /**
     * 这里的Context 必须用actiivty 不能用applicationContext
     *
     * @param context
     */
    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);//
        mView = View.inflate(context, R.layout.dialog_loading, null);
        mLoadingTextView = (TextView) mView.findViewById(R.id.mLoadingTextView);

        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }

    public LoadingDialog(Context context, String msg) {
        super(context, R.style.LoadingDialogStyle);
        mView = View.inflate(context, R.layout.dialog_loading, null);
        mLoadingTextView = (TextView) mView.findViewById(R.id.mLoadingTextView);
        if (!TextUtils.isEmpty(msg)) {
            mLoadingTextView.setText(msg);
            mLoadingTextView.setVisibility(View.VISIBLE);
        } else
            mLoadingTextView.setVisibility(View.GONE);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }

    @Override
    public void show() {
        try {
            if (this.isShowing())
                this.dismiss();
            else
                super.show();
            //setContentView（）一定要在show之后调用
            this.setContentView(mView);
        } catch (Exception e) {
            L.P(e);
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            L.P(e);
            //捕获 IllegalArgumentException 或者 BadTokenException
        }
    }

    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message))
            mLoadingTextView.setText(message);
    }

    public void setMessage(@StringRes int message) {
        mLoadingTextView.setText(message);
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    //RxJava取消注册，以避免内存泄露
    private void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unSubscribe();
    }

}
