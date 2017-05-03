package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DY on 2017/4/6.
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("123","===onHandleIntent===");
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
    }
}
