package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by DY on 2017/4/13.
 */

public class SocketActivity extends Activity {

    private TextView tv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        tv_show = (TextView) findViewById(R.id.tv_show);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket=new Socket("192.168.10.6",30000);
//                    Socket socket=new Socket();
                    socket.setSoTimeout(5000);
//                    socket.connect(new InetSocketAddress("192.168.10.6",30000),5000);
                    InputStream inputStream = socket.getInputStream();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    byte[] bytes=new byte[1024];
                    int len=0;
                    while ((len=inputStream.read(bytes))!=-1){
                        baos.write(bytes,0,len);
                    }
                    Log.e("SocketActivity","从服务器获取的数据："+baos.toString());
                    tv_show.setText(baos.toString());
                    baos.close();
                    socket.close();
                } catch (SocketTimeoutException e){
                    Log.e("SocketActivity","连接超时");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
