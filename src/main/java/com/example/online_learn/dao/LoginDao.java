package com.example.online_learn.dao;

import com.example.online_learn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginDao extends JpaRepository<User,Long> {
    @Query("from User where  uName=?1")
    User findUserByName(String uName);
}
