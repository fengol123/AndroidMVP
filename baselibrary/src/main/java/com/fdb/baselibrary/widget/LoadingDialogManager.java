package com.fdb.baselibrary.widget;

import android.app.Activity;
import android.content.DialogInterface;

import com.fdb.baselibrary.bean.BaseDisposable;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
public class LoadingDialogManager {
    private LoadingDialog mLoadingDialog;
    private int mLoadingCount;

    public void showLoading(Activity activity, BaseDisposable disposable) {
        mLoadingCount++;
        if (mLoadingDialog == null) {
            initDialog(activity);
        }
        mLoadingDialog.addSubscription(disposable);
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
