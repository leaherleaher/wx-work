package com.tf.train.wechattrain.service;

import net.sf.json.JSONObject;

import java.util.Map;

public interface MenuService {
    JSONObject getMenu(String accessToken);
    int createMenu(Map<String, Object> menu, String accessToken);
    int deleteMenu(String accessToken);
}
