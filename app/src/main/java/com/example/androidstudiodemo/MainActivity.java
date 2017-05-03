package com.example.androidstudiodemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ViewStub viewStub1;
    private ViewStub viewStub2;
    private Button btnShow;
    private boolean b=true;
    private boolean a=true;
    private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("123", "onCreate");
        if (savedInstanceState!=null){
            String save = savedInstanceState.getString("save", "");
            Log.e("123", "onCreate---恢复了abc数据");
        }
//        TextView textView= (TextView) findViewById(R.id.share);
//        textView.setText("发送");
//        initView();
    }
    private void initView(){
        viewStub1=(ViewStub) findViewById(R.id.viewstub1);
        viewStub2=(ViewStub) findViewById(R.id.viewstub2);
        btnShow=(Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(b){//条件符合 显示布局
                    viewStub1.inflate();
                    viewStub2.inflate();
                    Log.e(TAG, "b=" + b);
                    b=false;
                }else{
                    if(a){//条件不符合 这个viewStub已经回收了所以我们只能用GONE和VISIBLE
                        viewStub2.setVisibility(View.GONE);
                        viewStub1.setVisibility(View.GONE);
                        Log.e(TAG, "隐藏 A=" + a);
                        a=false;
                    }else{//条件符合
                        viewStub2.setVisibility(View.VISIBLE);
                        viewStub1.setVisibility(View.VISIBLE);
                        final TextView tv= (TextView) findViewById(R.id.textView1);
                        Log.e(TAG, "显示 A=" + a);
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tv.setText("点击改变文字");
                                Log.e(TAG, "点击了textview");
                            }
                        });
                        a=true;
                    }

                }

            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save", "abc");
        Log.e("123", "onSaveInstanceState---保存了abc数据");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String save = savedInstanceState.getString("save", "");
        Log.e("123", "onRestoreInstanceState---恢复了abc数据");
    }
}
