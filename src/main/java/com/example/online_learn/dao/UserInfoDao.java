package com.example.online_learn.dao;

import com.example.online_learn.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoDao extends JpaRepository<UserInfo,Long> , JpaSpecificationExecutor<UserInfo> {

    @Query("from UserInfo where user_id=?1")
    UserInfo findOneByUserId(String userid);
}
