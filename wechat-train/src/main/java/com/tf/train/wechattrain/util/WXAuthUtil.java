package com.tf.train.wechattrain.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 公用网络请求工具类
 */
public class WXAuthUtil {
    public static final String APPID="wx13d4875de26c0d12";
    public static final String APPSECRET ="616007cd26751377a0c71bcf058bad5a";
    private static final String TOKEN = "wxtoken";

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(null != entity){
            //把返回的结果转换为JSON对象
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSON.parseObject(result);
        }

        return jsonObject;
    }
}
