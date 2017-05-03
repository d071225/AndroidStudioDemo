package com.example.utils;

import android.app.Activity;

import com.example.androidfengkuangdemo.R;

/**
 * Created by DY on 2017/3/21.
 */

public class ChangeTheme {
    public static boolean flag=false;
    public static void setNight(Activity activity){
        if (flag){
            activity.setTheme(R.style.NightAppTheme);
        }else{
            activity.setTheme(R.style.DayAppTheme);
        }
    }
}
