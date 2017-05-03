package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyClass {
    public static void main(String[] args) {
        try {
            System.out.println("本机地址："+ InetAddress.getLocalHost());
            ServerSocket ss=new ServerSocket(30000);
            while (true){
                Socket accept = ss.accept();
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("您好，您收到了服务器的新年祝福！".getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
                accept.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
