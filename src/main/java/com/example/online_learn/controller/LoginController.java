package com.example.online_learn.controller;

import com.example.online_learn.dao.LoginDao;
import com.example.online_learn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private LoginDao loginDao;
    /**
     * 验证登陆信息
     */
    @RequestMapping("loginIn")
    public String loginIn(String username, String password, HttpServletRequest request, Model model){
        try {
            User user = loginDao.findUserByName(username);
            System.out.println(user.toString());
            if (user !=null){
                if (user.getuPassword().equals(password)){
                    request.setAttribute("user",user);
                    return "index";
                }else {
                    System.out.println("密码出错了");
                    model.addAttribute("loginMsg","密码不正确");
                    return "login";
                }
            }else {
                System.out.println("用户不存在");
                model.addAttribute("loginMsg","用户不存在");
                return "login";
            }
        }catch (NullPointerException e){
            System.out.println(e);
            System.out.println("抛异常，用户不存在");
            model.addAttribute("loginMsg","用户不存在");
            return "login";
        }

    }
}
