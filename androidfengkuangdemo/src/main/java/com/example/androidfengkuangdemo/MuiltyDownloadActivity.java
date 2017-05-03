package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.utils.DownLoadUtils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/4/14.
 */

public class MuiltyDownloadActivity extends Activity {

    private ProgressBar pb;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pb.setProgress(Integer.parseInt(msg.obj.toString()));
            if (Integer.parseInt(msg.obj.toString())>=100){
                Bitmap bitmap = BitmapFactory.decodeFile(MuiltyDownloadActivity.this.getFilesDir().getAbsolutePath() + File.separator + "download.jpg");
                iv.setImageBitmap(bitmap);
            }
        }
    };
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        pb = (ProgressBar) findViewById(R.id.pb);
        iv = (ImageView) findViewById(R.id.iv);
//        String url="http://img.ivsky.com/img/bizhi/pre/201703/06/lykan_hypersport-001.jpg";
        String url="http://img-download.pchome.net/download/1k1/8t/4u/oisdp1-1j58.jpg";
        final DownLoadUtils downLoadUtils=new DownLoadUtils(url,this.getFilesDir().getAbsolutePath()+ File.separator+"download.jpg",2);
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downLoadUtils.download();
                        final Timer timer=new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int progress= (int) (downLoadUtils.getCompleteRate()*100);
                                Message msg=new Message();
                                msg.obj=progress;
                                handler.sendMessage(msg);
                                if (progress>=100){
                                    timer.cancel();
                                }
                            }
                        },0,100);
                    }
                }).start();
            }
        });
    }
}
