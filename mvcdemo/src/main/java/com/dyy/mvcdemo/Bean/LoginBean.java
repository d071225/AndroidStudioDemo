package com.dyy.mvcdemo.Bean;

/**
 * Created by Daiyy on 2017/5/15.
 */

public class LoginBean {
    public LoginData REP_BODY;
    public class LoginData{
        /**
         *"custLogin": "",
         "RSPMSG": "登录成功!",
         "custId": "16091400000612",
         "custName": "pt002",
         "RSPCOD": "000000",
         "token": "d0ceab998e1042288c9dca06156537a2"
         */
        public String custLogin;
        public String RSPMSG;
        public String custId;
        public String custName;
        public String RSPCOD;
        public String token;
    }
}
