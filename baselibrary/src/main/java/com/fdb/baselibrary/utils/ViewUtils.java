package com.fdb.baselibrary.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/18.
 */
public class ViewUtils {
    public static View inflate(Context context, @LayoutRes int id) {
        return View.inflate(context, id, null);
    }
}
