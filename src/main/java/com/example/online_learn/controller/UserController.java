package com.example.online_learn.controller;

import com.example.online_learn.entity.Msg;
import com.example.online_learn.entity.Type;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.TypeService;
import com.example.online_learn.service.UserService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    @ResponseBody
    public List getUserList(){
        return userService.findUser();
    }

    /**
     * 获取用户操作
     * @param page
     * @param limit
     * @return
     */
    //获取用户list列表，并分页
    @RequestMapping("/getUserListWithPage")
    @ResponseBody
    public Page getUserListWithPage(Integer page,Integer limit){
        //分页操作
        PageRequest pageRequest =PageRequest.of(page-1, limit,Sort.by(Sort.Order.desc("userId")));
        Page<User> user = userService.findUser(pageRequest);
        System.out.println(user.getTotalElements());
        //遍历得到用户类型，并存入对应字段typeName
        for (User user1 : user) {
            //此处为空需要抛异常
            user1.setTypeName(user1.getType().getTypeName());
            user1.setTypeId(Long.toString(user1.getType().getTypeId()));
        }
        return user;
    }

    /**
     * 新增用户
     * @param username
     * @param typeid
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(String username, String typeid){
        System.out.println(username+"*******"+typeid);
        //判断该用户是否已存在
        User exist = userService.findUserByName(username);
        if (exist!=null){
            return Msg.success().add("msg","此用户已存在");
        }
        //先给外键赋值
        Type type = new Type();
        type.setTypeId(Long.valueOf(typeid).longValue());

        User user = new User();
        user.setUserName(username);
        user.setUserPassword(username);
        user.setType(type);
        User user1 = userService.saveUser(user);
        System.out.println(user1);
        if (user1!=null){
            return Msg.success().add("msg","添加成功");
        }else {
            return Msg.success().add("msg","添加失败");
        }
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Msg userDelete(String id){
        String userid[];
        int success=0;
        int fail=0;
        userid = id.split(",");
        for (String s : userid) {
            int i = userService.deleteUserById(s);
            if (i>0){
                success++;
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+userid.length+"个，成功"+success+"个，失败"+fail+"个");
    }

    /**
     * 更新用户信息
     * @param id
     * @param username
     * @param typeid
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Msg userUpdate(String id,String username,String typeid){
//        System.out.println(id+"****"+username+"*****"+typeid);
        //判断改用户名是否存在
        //判断该用户是否已存在
        User exist = userService.findUserByName(username);
        if (exist!=null){
            return Msg.success().add("msg","此用户名已被占用");
        }
        Type type = new Type();
        type.setTypeId(Long.valueOf(typeid));

        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setUserName(username);
        user.setUserPassword(username);
        user.setType(type);
        User updateUser = userService.saveUser(user);
        if (updateUser!=null){
            return Msg.success().add("msg","修改成功");
        }else {
            return Msg.success().add("msg","修改失败");
        }
    }

    /**
     * 模糊查询
     * @param username
     * @return
     */
    @RequestMapping("/finUserLike")
    @ResponseBody
    public Page finUserLike(String username,Integer page,Integer limit){
        //分页
        if (page==null || limit==null ){
            page=0;
            limit=10;
        }
        PageRequest pageRequest =PageRequest.of(page-1, limit,Sort.by(Sort.Order.desc("userId")));
        //模糊查询
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询属性
                Path userName = root.get("userName");
                Predicate like = criteriaBuilder.like(userName, "%" + username + "%");
                return like;
            }
        };

        Page<User> user = userService.findByUserNameLike(spec, pageRequest);
        //遍历得到用户类型，并存入对应字段typeName
        for (User user1 : user) {
            //此处为空需要抛异常
            user1.setTypeName(user1.getType().getTypeName());
        }
        return user;
    }



}
