package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DY on 2017/3/16.
 */

public class AsyncTaskActiviry extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        tv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task=new MyTask(AsyncTaskActiviry.this);
                try {
            task.execute(new URL("http://www.crazyit.org/ethos.php"));
//                    task.execute(new URL("http://www.sina.com.cn/"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public class MyTask extends AsyncTask<URL,Integer,String>{
        private int progress=0;
        private Context context;
        private ProgressDialog progressDialog;

        public MyTask(Context context){
            this.context=context;
        }
        @Override
        protected String doInBackground(URL... params) {
            try {
                HttpURLConnection conn= (HttpURLConnection) params[0].openConnection();
//                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                byte[] bytes=new byte[1024];
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                InputStream is=conn.getInputStream();
                int line=0;

//                String line=null;
//                StringBuilder sb=new StringBuilder();
                while ((line=is.read(bytes))!=-1){
//                        sb.append(line);
                        bos.write(bytes,0,line);
                    progress++;
                    publishProgress(progress);
                }
                is.close();
                bos.close();
                return new String(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("执行下载");
            progressDialog.setMessage("下载执行中，请稍等。。。");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText("已经读取了"+values[0]+"行");
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
//            progressDialog.dismiss();
        }
    }
}
