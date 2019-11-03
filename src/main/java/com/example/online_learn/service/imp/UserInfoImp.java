package com.example.online_learn.service.imp;

import com.example.online_learn.dao.UserInfoDao;
import com.example.online_learn.entity.UserInfo;
import com.example.online_learn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImp implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findUserInfo(String userid) {
        return userInfoDao.findOneByUserId(userid);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Override
    public int deleteUserInfo(String userinfoid) {
        try {
            userInfoDao.deleteById(Long.valueOf(userinfoid).longValue());
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Page findUserInfoListWithPage(PageRequest pageRequest) {
        return userInfoDao.findAll(pageRequest);
    }

    @Override
    public Page findUserInfoListByNameWithPage(Specification specification, PageRequest pageRequest) {
        return userInfoDao.findAll(specification,pageRequest);
    }
}
