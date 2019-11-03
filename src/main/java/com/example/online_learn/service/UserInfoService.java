package com.example.online_learn.service;

import com.example.online_learn.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface UserInfoService {

    //查找一个用户通过
    UserInfo findUserInfo(String userid);

    //添加用户,修改用户
    UserInfo saveUserInfo(UserInfo userInfo);

    //通过id删除用户
    int deleteUserInfo(String userinfoid);

    //查询所有用户，带上分页信息
    Page findUserInfoListWithPage(PageRequest pageRequest);

    //模糊查询带分页
    Page findUserInfoListByNameWithPage(Specification specification, PageRequest pageRequest);

}
