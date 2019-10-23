package com.example.online_learn.service.imp;

import com.example.online_learn.dao.UserDao;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUserById(String id) {
        return null;
    }

    @Override
    public int deleteUserById(String id) {
        return 0;
    }

    @Override
    public List<User> findUser() {
        return userDao.findAll();
    }

    @Override
    public List<User> findUserByName(String uName) {
        return userDao.findUserByName(uName);
    }
}
