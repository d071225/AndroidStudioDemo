package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/4/14.
 */

public class HttpClientActivity extends Activity {

    private TextView tv_show;
    private HttpClient httpClient;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123){
                tv_show.append(msg.obj.toString()+"\n");
            }else if(msg.what==0x234){
//                Toast.makeText(HttpClientActivity.this,msg.obj.toString(),0).show();
                Log.e("123","登录返回："+msg.obj.toString());
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpclient);
        tv_show = (TextView) findViewById(R.id.tv_show);
        tv_show.setText("");
        httpClient = new DefaultHttpClient();
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        HttpGet httpGet=new HttpGet("http://192.168.10.6:8080/foo/secret.jsp");
                        try {
                            HttpResponse httpResponse = httpClient.execute(httpGet);
                            HttpEntity entity = httpResponse.getEntity();
                            if (entity!=null){
                                BufferedReader br=new BufferedReader(new InputStreamReader(entity.getContent()));
                                String line=null;
                                while ((line=br.readLine())!=null){
                                    Message message=new Message();
                                    message.what=0x123;
                                    message.obj=line;
                                    handler.sendMessage(message);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(HttpClientActivity.this,R.layout.dialog_login_item,null);
                final EditText et_username= (EditText) view.findViewById(R.id.et_username);
                final EditText et_password= (EditText) view.findViewById(R.id.et_password);
                AlertDialog.Builder builder=new AlertDialog.Builder(HttpClientActivity.this);
                builder.setView(view);
                builder.setTitle("登录系统");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = et_username.getText().toString();
                        final String password = et_password.getText().toString();
                        new Thread(){
                            @Override
                            public void run() {
                                HttpPost post=new HttpPost("http://192.168.10.6:8080/foo/login.jsp");
                                try {
                                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("name",name));
                                    params.add(new BasicNameValuePair("pass",password));
                                    post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    HttpResponse httpResponse = httpClient.execute(post);
                                    HttpEntity entity = httpResponse.getEntity();
                                    if (httpResponse.getStatusLine().getStatusCode()==200){
//                                        BufferedReader br=new BufferedReader(new InputStreamReader(entity.getContent()));
                                        String line= EntityUtils.toString(entity);
//                                        while ((line=br.readLine())!=null){
                                        Looper.prepare();
                                        Toast.makeText(HttpClientActivity.this,line,0).show();
//                                            Message message=new Message();
//                                            message.what=0x234;
//                                            message.obj=line;
//                                            handler.sendMessage(message);
//                                        }
                                        Looper.loop();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
    }
}
