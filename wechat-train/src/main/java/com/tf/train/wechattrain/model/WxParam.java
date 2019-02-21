package com.tf.train.wechattrain.model;

import lombok.Data;

@Data
public class WxParam {
    private String timestamp;
    private String nonce;
    private String signature;
}
