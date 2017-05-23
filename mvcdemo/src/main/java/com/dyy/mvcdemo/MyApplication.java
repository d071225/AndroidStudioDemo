package com.dyy.mvcdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Daiyy on 2017/5/15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
