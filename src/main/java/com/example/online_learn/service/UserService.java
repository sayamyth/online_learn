package com.example.online_learn.service;

import com.example.online_learn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

    List<User> findUser();
    //添加用户,修改用户
    User saveUser(User user);

    //修改用户
    User updateUserById(String id);

    //删除用户根据Id
    int deleteUserById(String id);

    //直接得到所有用户,分页
    Page<User> findUser(PageRequest pageRequest);

    //模糊查询用户根据name
    Page<User> findByUserNameLike(Specification specification,PageRequest pageRequest);

    //查询用户根据name找出一个
    User findUserByName(String username);
}
