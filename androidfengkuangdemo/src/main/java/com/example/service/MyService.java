package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DY on 2017/4/6.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.e("123","===onStartCommand===");
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis()<endTime){
            synchronized (this){
                try {
                    wait(endTime- System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("123","===耗时任务执行完毕===");
        return START_STICKY;
    }
}
