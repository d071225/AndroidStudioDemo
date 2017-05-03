package com.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/4/13.
 */

public class MyServer {
    public static List<Socket> socketList=new ArrayList<Socket>();
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(30000);
            System.out.println("本机ip:"+ InetAddress.getLocalHost());
            while (true){
                Socket socket = ss.accept();
                socketList.add(socket);
                new Thread(new ServerThreed(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
