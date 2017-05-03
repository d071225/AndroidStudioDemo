package com.example.configchangesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("123", "onCreate");
        if (savedInstanceState!=null){
            String save = savedInstanceState.getString("save", "");
            Log.e("123", "onCreate---恢复了abc数据");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save", "abc");
        Log.e("123", "onSaveInstanceState---保存了abc数据");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String save = savedInstanceState.getString("save", "");
        Log.e("123", "onRestoreInstanceState---恢复了abc数据");
    }
}
