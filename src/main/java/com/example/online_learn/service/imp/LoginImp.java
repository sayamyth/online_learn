package com.example.online_learn.service.imp;

import com.example.online_learn.dao.LoginDao;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginImp implements LoginService {
    @Autowired
    LoginDao loginDao;
    @Override
    public User login(String username) {
        return loginDao.findByUserName(username);
    }
}
