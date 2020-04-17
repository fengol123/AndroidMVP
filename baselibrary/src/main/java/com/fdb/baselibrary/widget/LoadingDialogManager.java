package com.fdb.baselibrary.widget;

import android.app.Activity;
import android.content.DialogInterface;

import rx.Subscription;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class LoadingDialogManager {
    private LoadingDialog mLoadingDialog;
    private int mLoadingCount;

    public void showLoading(Activity activity, Subscription subscription) {
        mLoadingCount++;
        if (mLoadingDialog == null) {
            initDialog(activity);
        }
        mLoadingDialog.addSubscription(subscription);
    }

    private void initDialog(Activity activity) {
        mLoadingDialog = new LoadingDialog(activity);
        mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mLoadingCount = 0;
                mLoadingDialog = null;
            }
        });
        mLoadingDialog.show();
    }

    public void hideLoading() {
        if (mLoadingCount > 0) {
            mLoadingCount--;
        }

        if (mLoadingCount == 0) {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        }
    }
}
