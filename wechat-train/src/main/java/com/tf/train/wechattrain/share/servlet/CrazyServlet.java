package com.tf.train.wechattrain.share.servlet;

import java.util.Map;

import com.tf.train.wechattrain.share.util.Sign;
import com.tf.train.wechattrain.share.util.TickFile;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/")
public class CrazyServlet {


    public static String APPID = "wx13d4875de26c0d12";//appid 改为自己的appid
    public static String APPSECRET = "616007cd26751377a0c71bcf058bad5a";//红色
    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public String sharewx(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getParameter("url");
        Map<String, String> params;
        String jsonStr = null;
        try {
            params = Sign.sign(TickFile.getTicke(), url);
            System.out.println("sign----------  " + url);
            JSONObject jsonObject = JSONObject.fromObject(params);
            jsonObject.put("appid", APPID);
            jsonStr = jsonObject.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
