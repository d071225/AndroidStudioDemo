package com.dyy.mvcdemo.model;

import com.dyy.mvcdemo.callback.OnLoadCallBack;

/**
 * Created by Daiyy on 2017/5/15.
 */

public interface LoginModel {
    public void getLoginData(String username,String password,OnLoadCallBack<String> callBack);
}
