package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by DY on 2017/3/17.
 */

public class IntentActionActivity extends Activity {

    private String mIntentAction;
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        TextView nameTv= (TextView) findViewById(R.id.name);
        TextView ageTv= (TextView) findViewById(R.id.age);
        Intent mIntent=getIntent();
        mIntentAction = mIntent.getAction();
        if (Intent.ACTION_VIEW.equals(mIntentAction)){
            uri = mIntent.getData();
            if (uri!=null){
                String data=mIntent.getDataString();
                String scheme=uri.getScheme();
                String host=uri.getHost();
                String path=uri.getPath();
                String name=uri.getQueryParameter("name");
                String age=uri.getQueryParameter("age");
                Log.e("123","data===>"+data+"scheme===>"+scheme+"path===>"+path+"host===>"+host);
                Log.e("123","name===>"+name+"age===>"+age);
                nameTv.setText(name);
                ageTv.setText(age);
            }
        }
    }
}
