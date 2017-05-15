package com.dyy.mvcdemo.callback;

/**
 * Created by Daiyy on 2017/5/15.
 */

public interface OnLoadCallBack<T> {
    public void onSucess(T t);
    public void onErr();
}
