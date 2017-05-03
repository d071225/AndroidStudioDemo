package com.example.progressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private int no=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pb1.setProgress(no);
            pb2.setProgress(no);
        }
    };
    private ProgressBar pb1;
    private ProgressBar pb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (no<100){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                    no++;
                };
            }
        }).start();
    }
}
