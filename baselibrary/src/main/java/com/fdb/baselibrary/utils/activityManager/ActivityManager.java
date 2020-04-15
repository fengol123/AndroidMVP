package com.fdb.baselibrary.utils.activityManager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by xieguofeng on 2018/8/21
 * activity栈管理类
 */
public class ActivityManager {
    private static Stack<Activity> mActivityStack = new Stack<>(); //activity栈

    /**
     * 添加一个Activity到栈
     * @param activity
     */
    public static void addActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    /**
     * 从activity栈去除一个activity
     * @param activity
     */
    public static void removehActivity(Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 获取activity栈
     * @return
     */
    public static Stack<Activity> getAllActivity() {
        return mActivityStack;
    }

    /**
     * 获取activity栈顶的activity
     * @return
     */
    public static Activity getTopActivity() {
        if (mActivityStack.size() == 0) {
            return null;
        } else {
            return mActivityStack.peek();
        }
    }
}
