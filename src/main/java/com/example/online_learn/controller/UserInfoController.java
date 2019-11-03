package com.example.online_learn.controller;

import com.example.online_learn.entity.Klass;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.entity.User;
import com.example.online_learn.entity.UserInfo;
import com.example.online_learn.service.UserInfoService;
import com.example.online_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getUserInfoListWithPage")
    @ResponseBody
    public Page getUserInfoListWithPage(Integer page,Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("userInfoId")));
        Page<UserInfo> list = userInfoService.findUserInfoListWithPage(pageRequest);

            for (UserInfo userInfo : list) {
                //判断是否为空值
                if (userInfo.getKlass() != null && userInfo.getUser() !=null) {
                    userInfo.setClassName(userInfo.getKlass().getClassName());
                    userInfo.setUserName(userInfo.getUser().getUserName());
                    userInfo.setUserId(Long.toString(userInfo.getUser().getUserId()));
                    userInfo.setClassId(Long.toString(userInfo.getKlass().getClassId()));
                }
            }
        return list;

    }

    /**
     * 分页模糊查询
     * @param
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getUserInfoListByNameWithPage")
    @ResponseBody
    public Page getUserInfoListByNameWithPage(String userinfoname,Integer page,Integer limit){
        //分页
        if (page==null || limit==null ){
            page=0;
            limit=10;
        }
        PageRequest pageRequest =PageRequest.of(page-1, limit,Sort.by(Sort.Order.desc("userInfoId")));
        //模糊查询
        Specification<UserInfo> spec = new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询属性
                Path userName = root.get("userInfoName");
                Predicate like = criteriaBuilder.like(userName, "%" + userinfoname + "%");
                return like;
            }
        };
        Page<UserInfo> list = userInfoService.findUserInfoListByNameWithPage(spec, pageRequest);
        for (UserInfo userInfo : list) {
            //判断是否为空值
            if (userInfo.getKlass() != null && userInfo.getUser() !=null) {
                userInfo.setClassName(userInfo.getKlass().getClassName());
                userInfo.setUserName(userInfo.getUser().getUserName());
                userInfo.setUserId(Long.toString(userInfo.getUser().getUserId()));
                userInfo.setClassId(Long.toString(userInfo.getKlass().getClassId()));
            }
        }
        return list;
    }
    /**
     * 添加用户详情
     */
    @RequestMapping("/addUserInfo")
    @ResponseBody
    public Msg addUserInfo(String name,Integer classid,Integer userid){
//        System.out.println(name+classid+username);
//        //通过username获得userid
//        User exist = userService.findUserByName(username);
//        long userid;
//        //防止班级不存在时抛空异常
//        try {
//            userid = exist.getUserId();
//        }catch (Exception e){
//            return Msg.success().add("msg","该班级不存在");
//        }


        UserInfo info = userInfoService.findUserInfo(Long.toString(userid));
        if (info!=null){
            return Msg.success().add("msg","已有该生信息");
        }

        Klass klass = new Klass();
        klass.setClassId(classid);

        User user = new User();
        user.setUserId(userid);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoName(name);
        userInfo.setKlass(klass);
        userInfo.setUser(user);
        UserInfo saveUserInfo = userInfoService.saveUserInfo(userInfo);
        if (saveUserInfo != null){
            return Msg.success().add("msg","添加用户详情成功");
        }else {
            return Msg.success().add("msg","添加用户详情失败");
        }
    }

    /**
     *修改用户
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public Msg updateUserInfo(String userinfoid,String userinfoname,Integer classid,Integer userid){


        Klass klass = new Klass();
        klass.setClassId(classid);

        User user = new User();
        user.setUserId(userid);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoName(userinfoname);
        userInfo.setUserInfoId(Long.valueOf(userinfoid));
        userInfo.setKlass(klass);
        userInfo.setUser(user);
        UserInfo saveUserInfo = userInfoService.saveUserInfo(userInfo);
        if (saveUserInfo != null){
            return Msg.success().add("msg","修改用户详情成功");
        }else {
            return Msg.success().add("msg","修改用户详情失败");
        }
    }

    /**
     * 删除用户信息
     */
    @RequestMapping("/deleteUserInfo")
    @ResponseBody
    public Msg deleteUserInfo(String id){
        String ids[];
        ids = id.split(",");

        int success=0;
        int fail=0;

        for (String s : ids) {
            int i = userInfoService.deleteUserInfo(s);
            if (i>0){
                success++;
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+ids.length+"个，成功"+success+"个，失败"+fail+"个");
    }





}
