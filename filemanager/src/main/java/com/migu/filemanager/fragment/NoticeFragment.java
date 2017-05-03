package com.migu.filemanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DY on 2017/4/25.
 */

public class NoticeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        textView.setText("NoticeFragment");
        Log.e("123","===NoticeFragment onCreateView===");
        return textView;
    }
}
