package com.grechur.deskpet;

import android.app.Application;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class MyApplication extends Application{

    private static BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
        // debug模式开启数据库浏览器
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(mBoxStore).start(this);
        }

    }
    public static BoxStore getBoxStore() {
        return mBoxStore;
    }
}
