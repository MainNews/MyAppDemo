package com.example.hank.myappdemo.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by Jun on 2017/4/19.
 * 该类用来监听APP应用中内存溢出
 */

public class ExampleApplication extends Application {

    /**
     * 监听本该回收的对象
     */
    private RefWatcher refWatcher;
    private static Context context;//全局 上下文 ：ApplicationContext

    public static RefWatcher getRefWatcher(Context context) {
        ExampleApplication application = (ExampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
