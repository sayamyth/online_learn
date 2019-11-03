package com.example.online_learn.controller;

import com.example.online_learn.dao.LoginDao;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 验证登陆信息
     */
    @RequestMapping("loginIn")
    public String loginIn(String username, String password, HttpServletRequest request, Model model){
        User user = loginService.login(username);
        if (user!=null){
            if (user.getUserPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(600);
                return "index";
            }else {
                model.addAttribute("loginMsg","密码错误");
                return "login";
            }

        }else {
            model.addAttribute("loginMsg","账号不存在");
            return "login";
        }

    }
}
