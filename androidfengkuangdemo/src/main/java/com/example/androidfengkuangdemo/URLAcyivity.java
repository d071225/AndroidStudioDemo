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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DY on 2017/4/13.
 */

public class URLAcyivity extends Activity {

    private ImageView iv_download;
    private Bitmap bitmap1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            iv_download.setImageBitmap(bitmap1);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        iv_download = (ImageView) findViewById(R.id.iv_download);
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url=new URL("http://img.ivsky.com/img/bizhi/pre/201703/06/lykan_hypersport-001.jpg");
//                            URL url=new URL("http://www.crazyit.org/"
//                                    + "attachments/month_1008/20100812_7763e970f"
//                                    + "822325bfb019ELQVym8tW3A.png");
                            InputStream is = url.openStream();
//                            Bitmap bitmap= BitmapFactory.decodeStream(is);
                            BitmapFactory.Options options=new BitmapFactory.Options();
                            options.inJustDecodeBounds=true;
                            options.inSampleSize=2;
                            options.inJustDecodeBounds=false;
                            bitmap1 = BitmapFactory.decodeStream(is, null, options);
                            is=url.openStream();
                            FileOutputStream fos=openFileOutput("car.jpg",MODE_PRIVATE);
                            byte[] bytes=new byte[1024];
                            int len=0;
                            while ((len=is.read(bytes))!=-1){
                                fos.write(bytes,0,len);
                            }
                            handler.sendEmptyMessage(0);
                            fos.close();
                            is.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
