package com.example.online_learn.dao;

import com.example.online_learn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    @Query("from User where  uName like %?1%")
    List<User> findUserByName(String uName);


}
