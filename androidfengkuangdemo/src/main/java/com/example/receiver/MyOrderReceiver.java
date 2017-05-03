package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by DY on 2017/4/10.
 */

public class MyOrderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
        Log.e("123","收到处理过的action为："+action+",收到处理过的值为："+msg);
        Bundle resultExtras = getResultExtras(true);
        String first = resultExtras.getString("first");
        Log.e("123","收到处理过的广播信息"+first);
    }
}
