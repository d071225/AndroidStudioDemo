package com.example.androidfengkuangdemo;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DY on 2017/3/31.
 */

public class Words {
    public static final String AUTHORITY="com.example.providerwords.MyProvider";
    public static final class word implements BaseColumns{
        public final static String _ID="_id";
        public final static String WORD="word";
        public final static String DETAIL="detail";
        public final static Uri DICT_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/words");
        public final static Uri WORD_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/word");
    }
}
