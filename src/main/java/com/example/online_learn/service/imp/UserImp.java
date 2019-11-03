package com.example.online_learn.service.imp;

import com.example.online_learn.dao.UserDao;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findUser() {
        return userDao.findAll();
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUserById(String id) {

        return null;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUserById(String id) {
        try {
            userDao.deleteById(Long.valueOf(id).longValue());
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 得到所有用户信息,带上分页
     * @return
     */
    @Override
    public Page findUser(PageRequest pageRequest) {

        return userDao.findAll(pageRequest);
    }

    @Override
    public Page<User> findByUserNameLike(Specification specification,PageRequest pageRequest) {
        return userDao.findAll(specification,pageRequest);
    }


    /**
     * 模糊查询
     * @param username
     * @return
     */

    /**
     * 通过用户名查找到用户
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        return userDao.findByUserName(username);
    }


}
