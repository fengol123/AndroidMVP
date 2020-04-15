package com.fdb.baselibrary.base;

import android.app.Application;

import com.fdb.baselibrary.utils.activityManager.BaseActivityLifecycleCallbacks;
import com.squareup.leakcanary.LeakCanary;

/**
 * Application基类 已实现以下功能
 * <pre>
 *     1.监听所有activity的生命周期
 *     2.内存泄漏检测初始化
 *     3.获取app单例
 *     4.Logger初始化
 *     5.Arouter初始化
 * </pre>
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
//    private static ConfigModule mConfigModule; //全局配置
    //监听所有activity的生命周期
    private BaseActivityLifecycleCallbacks mActivityLifeCallbacks = new BaseActivityLifecycleCallbacks();

    /**
     * 获取Application实例
     */
    public static BaseApplication getApp() {
        return mInstance;
    }

//    public static ConfigModule getConfigModule() {
//        return mConfigModule;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        //LeakCanary初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        mInstance = this;

        //监听所有activity的生命周期
        registerActivityLifecycleCallbacks(mActivityLifeCallbacks);

        //初始化config
//        mConfigModule = new ManifestParser(this).parse();


        initialize();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        //取消监听所有activity的生命周期
        unregisterActivityLifecycleCallbacks(mActivityLifeCallbacks);
    }

    /**
     * 重写这个方法,进行初始化
     */
    protected void initialize() {
    }
}
