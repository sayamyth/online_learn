package com.example.online_learn.service;

import com.example.online_learn.entity.User;

import java.util.List;

public interface UserService {
    //添加用户
    User addUser(User user);

    //修改用户
    User updateUserById(String id);

    //删除用户根据Id
    int deleteUserById(String id);

    //直接得到所有用户
    List<User> findUser();

    //查询用户根据name
    List<User> findUserByName(String uName);
}
