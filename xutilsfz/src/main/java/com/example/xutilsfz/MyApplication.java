package com.example.xutilsfz;

import android.app.Application;

import org.xutils.x;

/**
 * Created by DY on 2017/3/7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
