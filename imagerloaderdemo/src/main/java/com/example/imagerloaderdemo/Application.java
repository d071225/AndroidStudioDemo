package com.example.imagerloaderdemo;

/**
 * Created by DY on 2016/12/23.
 */
public class Application extends android.app.Application {
    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
