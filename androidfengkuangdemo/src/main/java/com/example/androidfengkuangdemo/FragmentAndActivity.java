package com.example.androidfengkuangdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragment.MyFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/17.
 */

public class FragmentAndActivity extends FragmentActivity implements MyFragment.MyFragmentListener{

    private FragmentTransaction fragmentTransaction;
    private TextView tv;
    private MyActivityListener listener;
    private MyFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        findView();
        setFragment();
    }

    private void setFragment() {
        FragmentManager manager=getSupportFragmentManager();
        fragmentTransaction = manager.beginTransaction();
        fragment = new MyFragment();

        fragmentTransaction.replace(R.id.fl, fragment);
        fragmentTransaction.commit();
    }

    private void findView() {
        tv = (TextView) findViewById(R.id.tv);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.tv.setText("activity控制fragment数据刷新");
            }
        });
    }

    @Override
    public void setData(Bundle bundle) {
        tv.setText(bundle.getString("fragment"));
    }

    @Override
    public void comm() {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        },5000);
    }
    public interface MyActivityListener{
        public void setData(Bundle bundle);
    }
    public void setMyActivityListener(MyActivityListener listener){
        this.listener=listener;
    }
}
