package com.example.online_learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此处进行一些页面的跳转控制
 */
@Controller
public class JumpController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }
        @RequestMapping("userList")
    public String userList(){
        return "user/list";
    }
}
