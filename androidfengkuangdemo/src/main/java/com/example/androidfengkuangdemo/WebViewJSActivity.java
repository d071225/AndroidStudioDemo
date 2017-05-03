package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by DY on 2017/4/17.
 */
public class WebViewJSActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewjs);
        final WebView wv= (WebView) findViewById(R.id.wb_js);
        Button btn_calljs= (Button) findViewById(R.id.btn_calljs);
        btn_calljs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.loadUrl("javascript:androidcalljs()");
            }
        });
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/test.html");
        wv.addJavascriptInterface(new MyObject(this), "myObj");
        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }
    public class MyObject {
        private Context context;

        public MyObject(Context context) {
            this.context = context;
        }
        @JavascriptInterface
        public void showToast(String name){
            Toast.makeText(context,name+",您好",0).show();
        }
        @JavascriptInterface
        public void showList(){
                    new AlertDialog.Builder(context)
                    .setTitle("图书列表")
                            .setIcon(R.drawable.android)
                    .setItems(new String[]{"三国演义", "水浒传", "西游记", "红楼梦"}, null)
                    .setPositiveButton("确定", null)
//                    .create()
                    .show();
        }
//        public void showList()
//        {
//            // 显示一个普通的列表对话框
//            new AlertDialog.Builder(context)
//                    .setTitle("图书列表")
//                    .setIcon(R.mipmap.ic_launcher)
//                    .setItems(new String[]{"疯狂Java讲义"
//                            , "疯狂Android讲义" , "轻量级Java EE企业应用实战"} , null)
//                    .setPositiveButton("确定", null)
//                    .create()
//                    .show();
//        }
    }
}
