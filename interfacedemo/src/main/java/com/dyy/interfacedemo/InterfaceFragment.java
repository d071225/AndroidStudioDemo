package com.dyy.interfacedemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Daiyy on 2017/5/9.
 */

public class InterfaceFragment extends Fragment {
    FinishAppInterface finishAppInterface = (FinishAppInterface) getActivity();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment_interface,null);
        final Button finish= (Button) view.findViewById(R.id.btn_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finishAppInterface.finishApp();
                MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.finishApp();
//                getActivity().startActivity(new Intent(getActivity(),SecendActivity.class));
            }
        });
        return view;
    }
}
