package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DY on 2017/4/5.
 */

public class ContentObserverActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContentResolver().registerContentObserver(Uri.parse("content://sms"),true,new SmsObserver(new Handler()));
    }
    public class SmsObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SmsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/outbox"), null, null, null, null);
            while (cursor.moveToNext()){
                StringBuilder sb=new StringBuilder();
                sb.append("短信地址:"+cursor.getString(cursor.getColumnIndex("address")));
                sb.append("，主题:"+cursor.getString(cursor.getColumnIndex("subject")));
                sb.append("，内容:"+cursor.getString(cursor.getColumnIndex("body")));
                sb.append("，时间:"+cursor.getString(cursor.getColumnIndex("date")));
                Log.e("123",sb.toString());
            }
        }
    }

}
