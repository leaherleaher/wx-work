package com.tf.train.wechattrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Hello {

    @RequestMapping(value="/hello")
    public String hello(){
        return "index";
    }

}
