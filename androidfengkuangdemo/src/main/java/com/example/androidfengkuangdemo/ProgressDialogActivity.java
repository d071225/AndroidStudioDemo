package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by DY on 2017/3/16.
 */

public class ProgressDialogActivity extends Activity {
    private int progress=0;
    private ProgressDialog pd3;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd3.setProgress(progress);
            Log.e("123","progress==="+progress);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Button btn_one= (Button) findViewById(R.id.btn_one);
        Button btn_two= (Button) findViewById(R.id.btn_two);
        Button btn_three= (Button) findViewById(R.id.btn_three);
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.show(ProgressDialogActivity.this,"任务执行","执行任务中，请等待！",false,true);
            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pd2=new ProgressDialog(ProgressDialogActivity.this);
                pd2.setTitle("任务执行中");
                pd2.setMessage("任务执行中，请稍等。。。");
                pd2.setCancelable(true);
                pd2.setIndeterminate(true);
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd2.show();
            }
        });
        btn_three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pd3 = new ProgressDialog(ProgressDialogActivity.this);
                pd3.setTitle("任务执行中");
                pd3.setMessage("任务执行中，请稍等。。。");
                pd3.setCancelable(false);
                pd3.setIndeterminate(false);
                pd3.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd3.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        progress=0;
                        while (progress<100){
                            try {
                                Thread.sleep(100);
                                mHandler.sendEmptyMessage(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progress++;
//                            if (progress==100){
//                                pd3.dismiss();
//                            }
                        }
                    }
                }).start();
            }
        });
    }
}
