package com.example.online_learn.service;

import com.example.online_learn.entity.CourseWareType;

import java.util.List;

public interface CourseWareTypeService {

    //得到列表
    List<CourseWareType> findCourseWareType();
    //添加修改课件类型
    CourseWareType saveCourseWareType(CourseWareType courseWareType);
    //删除课件
    int deleteCourseWareType(String id);
}
