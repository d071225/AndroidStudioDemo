package com.dyy.mvcdemo.iml;

import android.content.Context;
import android.content.Intent;

import com.dyy.mvcdemo.Bean.LoginBean;
import com.dyy.mvcdemo.callback.OnLoadCallBack;
import com.dyy.mvcdemo.controller.SecondActivity;
import com.dyy.mvcdemo.model.LoginModel;
import com.dyy.mvcdemo.utils.L;
import com.dyy.mvcdemo.utils.T;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by Daiyy on 2017/5/15.
 */

public class LoginModelIml implements LoginModel {
    private Context context;
    public LoginModelIml(Context context) {
        this.context=context;
    }

    public String objectToJson(String name, String password) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("REQ_HEAD", new JSONObject());
            JSONObject body=new JSONObject();
            body.put("custMobile", name);
            body.put("custPwd", password);
            jsonObject.put("REQ_BODY", body);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void getLoginData(String username, String password, final OnLoadCallBack<String> callBack) {
        RequestParams params=new RequestParams("http://wx.mposbank.com/tdcctp/SY0003.tran");
        params.addBodyParameter("REQ_MESSAGE", objectToJson(username,password));
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                L.e("查询成功===》" + result);
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                LoginBean.LoginData loginData = loginBean.REP_BODY;
                if (loginData.RSPCOD.equals("000000")) {
                    String custId = loginData.custId;
                    String custName = loginData.custName;
                    String RSPMSG = loginData.RSPMSG;
                    String token = loginData.token;

//                    context.startActivity(new Intent(context, SecondActivity.class));
                    T.showShort(context,"登录成功");
                } else if (loginData.RSPCOD.equals("000206")) {
                    context.startActivity(new Intent(context, SecondActivity.class));
                    T.showShort(context,"用户不存在");
                } else {
                    T.showShort(context,"用户或密码不正确");
                }
              callBack.onSucess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

}
