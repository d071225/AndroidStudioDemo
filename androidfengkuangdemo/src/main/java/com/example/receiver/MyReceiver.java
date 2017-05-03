package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by DY on 2017/4/10.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
        Log.e("123","收到的action为："+action+",收到的值为："+msg);
        Bundle bundle=new Bundle();
        bundle.putString("first","第一个收到的广播");
        setResultExtras(bundle);
        abortBroadcast();
    }
}
