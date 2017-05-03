package com.example.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DY on 2017/4/14.
 */

public class DownLoadUtils {
    private String path;
    private String targetFile;
    private int threadNum;
    private DownThread[] downThreads;
    private int contentLength;

    public DownLoadUtils(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        downThreads=new DownThread[threadNum];
    }

    public void download(){
        try {
            URL url=new URL(path);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            contentLength = con.getContentLength();
            Log.e("123","下载文件大小："+contentLength);
            con.disconnect();
            int partSize= contentLength /threadNum+1;
            RandomAccessFile file=new RandomAccessFile(targetFile,"rw");
            file.setLength(contentLength);
            file.close();
            for (int i = 0; i < threadNum; i++) {
                int downLoadPosition=i*partSize;
                RandomAccessFile currentPart=new RandomAccessFile(targetFile,"rw");
                currentPart.seek(downLoadPosition);
                downThreads[i]=new DownThread(partSize,downLoadPosition,currentPart);
                downThreads[i].start();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double getCompleteRate(){
        int sumSize=0;
        for (int i = 0; i <threadNum ; i++) {
            sumSize+=downThreads[i].partLenghth;
        }
        return sumSize*1.0f/contentLength;
    }
    public class DownThread extends Thread{
        private int partSize;
        private int downLoadPosition;
        private RandomAccessFile currentPart;
        private int partLenghth;
        public DownThread(int partSize, int downLoadPosition, RandomAccessFile currentPart) {
            this.partSize = partSize;
            this.downLoadPosition = downLoadPosition;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                URL url=new URL(path);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5*1000);
                conn.setRequestMethod("GET");
                InputStream is = conn.getInputStream();
                skipFully(is,downLoadPosition);
                byte[] bytes=new byte[1024];
                int len=0;
                while ((len=is.read(bytes))>0){
                    currentPart.write(bytes,0,len);
                    partLenghth+=len;
                }
                currentPart.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void skipFully(InputStream is,long bytes){
        long remaining=bytes;
        while (remaining>0){
            try {
                long skip = is.skip(remaining);
                Log.e("123","remaining"+remaining+",skip:"+skip);
                remaining-=skip;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
