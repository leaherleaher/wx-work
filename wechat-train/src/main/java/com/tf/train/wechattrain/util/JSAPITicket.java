package com.tf.train.wechattrain.util;

import com.tf.train.wechattrain.config.Contants;
import com.tf.train.wechattrain.model.WxParam;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JSAPITicket {
    public void getJsapi_ticket(HttpServletRequest req, HttpServletResponse resp) throws ClientProtocolException, IOException {
        String  access_token=Contants.ACCESS_TOKEN;
        System.out.println("access_token==="+access_token);
        String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
        //JSONObject jsonObject=WeiXinUtil.doGetStr(url);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        System.out.println(jsonObject);
        String ticket=jsonObject.getString("ticket");
        Contants.JSAPI_TICKET=jsonObject.getString("ticket");
        Contants.ticket_expires_in=jsonObject.getInt("expires_in");
    }

    public WxParam getEncryptJsapiTicket( HttpServletRequest req , HttpServletResponse resp, @RequestParam("url") String url) throws UnsupportedEncodingException {
        String ticket=Contants.JSAPI_TICKET;
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//随机生成的时间戳
        //String noncestr = CommonUtil.getRandomString(16);//生成的16位随机码
        String noncestr = "aaaaaaaaaaaaaaaa";
        String url1=java.net.URLDecoder.decode(url.split("=")[1],"UTF-8");//在调用js的页面完整URL
        String signature=getSignature(url1, timestamp, noncestr);//开始进行sha1签名
        WxParam wxparam=new WxParam();
        wxparam.setNonce(noncestr);
        wxparam.setSignature(signature);
        wxparam.setTimestamp(timestamp);
        return wxparam;
    }

    public static String getSignature(String url,String timeStamp,String nonceStr){
        //所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1：
        String signValue = "jsapi_ticket="+Contants.JSAPI_TICKET+"&noncestr="+nonceStr+"×tamp="+timeStamp+"&url="+url;
        String signature =DigestUtils.sha1Hex(signValue);
        return signature;
    }
}
