package com.tf.train.wechattrain.controller;

import com.alibaba.fastjson.JSONObject;
import com.tf.train.wechattrain.util.WXAuthUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/wx")
public class WxLoginController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxLoginController.class);

    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public void wxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义成功回调地址
        String backUrl = "http://hx24xr.natappfree.cc/hello";
        //第一步 用户同意授权 获取code
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXAuthUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(backUrl,"UTF-8")
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";

        logger.info("forward重定向地址{" + url + "}");

        response.sendRedirect(url);
    }

    @RequestMapping(value="/callBack")
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 获取微信基本信息
         */
        String code = request.getParameter("code");
        //第二步  通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WXAuthUtil.APPID
                + "&secret=" + WXAuthUtil.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";

        JSONObject jsonObject = WXAuthUtil.doGetJson(url);
        //获取用户信息
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");
        //校验token是否失效
        String chickUrl = "https://api.weixin.qq.com/sns/auth?access_token=" + access_token + "&openid=" + openid;
        JSONObject chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
        System.out.println(chickuserInfo.toString());
        if (!"0".equals(chickuserInfo.getString("errcode"))) {
            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + openid + "&grant_type=refresh_token&refresh_token=" + refresh_token;

            JSONObject refreshInfo = WXAuthUtil.doGetJson(chickUrl);
            System.out.println(refreshInfo.toString());
            access_token = refreshInfo.getString("access_token");
        }
        //第四步 拉取用户信息
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token
                + "&openid=" + openid
                + "&lang=zh_CN";
        System.out.println("infoUrl:" + infoUrl);
        JSONObject userInfo = WXAuthUtil.doGetJson(infoUrl);

        System.out.println("JSON-----" + userInfo.toString());
        System.out.println("名字-----" + userInfo.getString("nickname"));
        System.out.println("头像-----" + userInfo.getString("headimgurl"));
        /*
         * end 获取微信用户基本信息
         */
        //获取到用户信息后就可以进行重定向，走自己的业务逻辑了。。。。。。
        //接来的逻辑就是你系统逻辑了，请自由发挥

        return "login";
    }

}

