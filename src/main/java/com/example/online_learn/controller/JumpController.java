package com.example.online_learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此处进行一些页面的跳转控制
 */
@Controller
public class JumpController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 用户功能相关跳转
     * @return
     */
    //用户列表
    @RequestMapping("toUserList")
    public String userList(){
        return "user/user-list";
    }
    //用户添加
    @RequestMapping("/toUserAdd")
    public String userAdd(){
        return "user/user-add";
    }
    //修改用户
    @RequestMapping("/toUserUpdate")
    public String toUserUpdate(){
        return "user/user-update";
    }
    /**
     * 用户权限相关跳转
     */
    //用户权限列表
    @RequestMapping("toTypeList")
    public String typeList(){
        return "user/type-list";
    }
    //用户权限新增
    @RequestMapping("/toTypeAdd")
    public String toTypeAdd(){
        return "user/type-add";
    }
    //用户权限修改
    @RequestMapping("/toTypeUpdate")
    public String toTypeUpdate(){
        return "user/type-update";
    }

    /***
     * 班级相关跳转
     */
    @RequestMapping("/toClassList")
    public String toClassList(){
        return "class/class-list";
    }
    @RequestMapping("/toClassAdd")
    public String toClassAdd(){
        return "class/class-add";
    }
    @RequestMapping("/toClassUpdate")
    public String toClassUpdate(){
        return "class/class-update";
    }


    /**
     * 用户详情管理
     */
    @RequestMapping("/toUserInfoList")
    public String toUserInfoList(){
        return "userInfo/userInfo-list";
    }
    @RequestMapping("/toUserInfoAdd")
    public String toUserInfoAdd(){
        return "userInfo/userInfo-add";
    }
    @RequestMapping("/toUserInfoUpdate")
    public String toUserInfoUpdate(){
        return "userInfo/userInfo-update";
    }

    /**
     * 课程跳转
     */
    @RequestMapping("/toCourseList")
    public String toCourseList(){
        return "course/course-list";
    }
    @RequestMapping("/toCourseAdd")
    public String toCourseAdd(){
        return "course/course-add";
    }
    @RequestMapping("/toCourseUpdate")
    public String toCourseUpdate(){
        return "course/course-update";
    }
    /**
     * 课件跳转
     */
    @RequestMapping("/toCourseWareList")
    public String toCourseWareList(){
        return "courseWare/courseWare-list";
    }
    @RequestMapping("/toCourseWareAdd")
    public String toCourseWareAdd(){
        return "courseWare/courseWare-add";
    }
    @RequestMapping("/toCourseWareUpdate")
    public String toCourseWareUpdate(){
        return "courseWare/courseWare-update";
    }
    /**
     * 问题
     */
    @RequestMapping("/toQuestionList")
    public String toQuestionList(){
        return "question/question-list";
    }
    @RequestMapping("/toQuestionAdd")
    public String toQuestionAdd(){
        return "question/question-add";
    }
    @RequestMapping("/toQuestionUpdate")
    public String toQuestionUpdate(){
        return "question/question-update";
    }
}
