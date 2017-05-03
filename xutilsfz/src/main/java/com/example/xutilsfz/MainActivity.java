package com.example.xutilsfz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private String url="http://118.89.97.223/tdcctp/alipay/app_pay.tran";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

            }
        });
    }

    private void getData() {
//        RequestParams params=new RequestParams(url);

        Map<String, String> map=new TreeMap<String, String>();
        map.put("merchant_order_no", "1234");
//		map.put("merchant_no", "16111600000717");
        map.put("merchant_no", "210017022700010687");
        map.put("callback_url", "http://www.baidu.com");
        map.put("order_smt_time", "20170215");
        map.put("order_type", "02");
        map.put("trade_amount", "1");
        map.put("goods_name", "支付宝测试");
        map.put("goods_type", "02");
        map.put("trade_desc", "测试");
//        for(Map.Entry<String, String> entry:map.entrySet()){
//            params.addBodyParameter(entry.getKey(), entry.getValue());
//        }
//        params.addBodyParameter("sign",getSign(map) );
        Xutils.getInstance().post(url, map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                Log.e("123","结果===》"+result);
            }
        });
    }
    /**
     * 传入排序后的集合再进行加密
     * @param params
     * @return
     */
    public static String getSign(Map<String, String> params) {
//        String signContent=JSON.toJSONString(params);
//        //"^#20160913&deyfpay%<)!="为MD5加密密钥
//        String sign=MD5Util.string2MD5(signContent, "^#20160913&deyfpay%<)!=");
        return null;
    }
}
