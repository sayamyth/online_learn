package com.example.online_learn.dao;

import com.example.online_learn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {


//    Page<User> findByUserNameLike(Specification s, PageRequest pageRequest);
    //通过用户名查询用户，为添加做准备，不能添加重复用户名
    User findByUserName(String username);



}
