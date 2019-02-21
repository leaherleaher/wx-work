package com.tf.train.wechattrain.model.req;

import lombok.Data;

@Data
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;
}
