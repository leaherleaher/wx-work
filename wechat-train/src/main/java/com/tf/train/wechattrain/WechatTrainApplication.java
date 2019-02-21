package com.tf.train.wechattrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 这里，启用定时任务
public class WechatTrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatTrainApplication.class, args);
    }
}
