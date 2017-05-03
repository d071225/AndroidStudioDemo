package com.example.webviewprogressdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private WebView wb;
    private TextView tv;
    private ProgressBar pb;
    private String url="intent://myhost/mypath?name=\"zhangsan\"&age=\"25\"";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wb = (WebView) findViewById(R.id.webview);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tv.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setProgress(newProgress);
            }
        });
        wb.setWebViewClient(new WebViewClient() {
                                @Override
                                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                    super.onPageStarted(view, url, favicon);
                                    pb.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onPageFinished(WebView view, String url) {
                                    super.onPageFinished(view, url);
                                    pb.setVisibility(View.GONE);
                                }

                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                    if (url.startsWith("intent://")){
                                        try {
                                            Intent intent=Intent.parseUri(url,Intent.URI_INTENT_SCHEME);
                                            intent.addCategory("android.intent.category.BROWSABLE");
                                            startActivity(intent);
                                        } catch (URISyntaxException e) {
                                            e.printStackTrace();
                                        }
                                        return true;
                                    }else{
                                        wb.loadUrl(url);
                                        return false;
                                    }
                                }
                            }

        );
        wb.loadUrl(url);
    }
}
