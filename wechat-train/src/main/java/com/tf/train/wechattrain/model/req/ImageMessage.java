package com.tf.train.wechattrain.model.req;

import lombok.Data;

@Data
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;
}
