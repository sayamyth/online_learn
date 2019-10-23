package com.example.online_learn.controller;

import com.example.online_learn.dao.UserDao;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    @ResponseBody
    public String saveOne(){
        User user = new User();
        user.setuName("111");
//        User user1 = userService.selectUserByName(user);
        return userService.findUser().toString();
    }

    @RequestMapping("/test1")
    @ResponseBody
    public Map findOne(){
        List<User> user1 = userDao.findUserByName("11");
        Map<String,Object> map = new HashMap<>();
        map.put("data",user1);
        return map;
    }


}
