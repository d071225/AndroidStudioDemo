package com.example.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by DY on 2017/4/13.
 */

public class ClientThread implements Runnable {
    private Handler handler;
    private Socket socket;
    private BufferedReader br;
    private OutputStream os;
    public  Handler reqHandler;

    public ClientThread(Handler handler) {
        this.handler=handler;
    }

    @Override
    public void run() {
        socket= new Socket();
        try {
            socket.connect(new InetSocketAddress("192.168.10.6",30000),30000);
//            socket.setSoTimeout(30000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();
            new Thread(){
                @Override
                public void run() {
                    String content=null;
                    try {
                        while ((content=br.readLine())!=null){
                            Message msg=new Message();
                            msg.what=0x123;
                            msg.obj=content;
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            Looper.prepare();
            reqHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what==0x234){
                        try {
                            os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e){
            Log.e("ClientThread","连接超时");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
