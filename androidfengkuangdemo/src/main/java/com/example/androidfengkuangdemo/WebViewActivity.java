package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.EditText;

/**
 * Created by DY on 2017/4/14.
 */

public class WebViewActivity extends Activity {

    private EditText et_url;
    private WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        et_url = (EditText) findViewById(R.id.et_url);
        wv = (WebView) findViewById(R.id.wv);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_A){
            String url = et_url.getText().toString();
            wv.loadUrl(url);
            return true;
        }
        return false;
    }
}
