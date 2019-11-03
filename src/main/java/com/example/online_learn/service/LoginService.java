package com.example.online_learn.service;

import com.example.online_learn.entity.User;

import java.util.List;

public interface LoginService {
    /**
     * 查找用户，实现登陆功能
     */
    User login(String username);
}
