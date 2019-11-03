package com.example.online_learn.dao;

import com.example.online_learn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface LoginDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    User findByUserName(String username);
}
