package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidfengkuangdemo.R;

/**
 * Created by DY on 2017/3/17.
 */

public class MyFragment extends Fragment{

    private Bundle arguments;
    private String content;
    private MyFragmentListener listener;
    public TextView tv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        arguments = getArguments();
//        content = arguments.getString("tv");
        listener= (MyFragmentListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_demo,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        tv = (TextView) view.findViewById(R.id.fragment_tv);
        final Button btn= (Button) view.findViewById(R.id.btn);
//        tv.setText(content);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("fragment","fragment测试数据");
                listener.setData(bundle);
//                listener.comm();
            }
        });
    }

    public interface MyFragmentListener{
            public void setData(Bundle bundle);
            public void comm();
    }
    public void setMyFragmentListener(MyFragmentListener listener){
        this.listener=listener;
    }
}
