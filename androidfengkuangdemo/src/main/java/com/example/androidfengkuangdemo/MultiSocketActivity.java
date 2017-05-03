package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thread.ClientThread;



/**
 * Created by DY on 2017/4/13.
 */

public class MultiSocketActivity extends Activity {

    private TextView tv_receiver;
    private EditText et_content;
    private Button btn_send;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123){
//                Log.e("MultiSocketActivity","客户端收到信息："+msg.obj.toString());
                tv_receiver.append("\n"+msg.obj.toString());
            }
        }
    };
    private ClientThread clientThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multisocket);
        tv_receiver = (TextView) findViewById(R.id.tv_receiver);
        tv_receiver.append("客户端收到内容：");
        et_content = (EditText) findViewById(R.id.et_content);
        btn_send = (Button) findViewById(R.id.btn_send);
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString();
                Message message=new Message();
                message.what=0x234;
                message.obj=content;
                clientThread.reqHandler.sendMessage(message);
                et_content.setText("");
            }
        });
    }
}
