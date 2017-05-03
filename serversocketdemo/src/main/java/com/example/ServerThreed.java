package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * Created by DY on 2017/4/13.
 */

public class ServerThreed implements Runnable {
    private Socket socket;
    private BufferedReader br;
    public ServerThreed(Socket socket) {
        this.socket=socket;
        try {
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String content=null;
//        System.out.println("服务端收到信息："+readFromClient());
        while ((content=readFromClient())!=null){
            System.out.println("进入循坏："+content);
            for (Iterator<Socket> it=MyServer.socketList.iterator();it.hasNext();){
                Socket s=it.next();
                try {
                    System.out.println("获取socket实例："+content);
                    OutputStream os = s.getOutputStream();
                    os.write((content+"\n").getBytes("utf-8"));
                    System.out.println("服务端发送信息："+content);
                } catch (IOException e) {
                    e.printStackTrace();
                    it.remove();
                    System.out.println(MyServer.socketList);
                }
            }
        }
    }
    public String readFromClient(){
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.socketList.remove(socket);
        }
        return null;
    }
}
