package com.example.online_learn.service;

import com.example.online_learn.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourseService {
    //保存或者修改课程
    Course saveCourse(Course course);

    //得到课程列表
    List<Course> getCourseList();

    //得到课程列表，带分页
    Page getCourseListWithPage(PageRequest pageRequest);

    //模糊查询得到课程列表。带分页
    Page getCourseListByNameWithPage(Specification specification, PageRequest pageRequest);

    //通过id删除课程
    int deleteCourse(String courseid);

    //通过名字查询课程
    Course findOneByName(String coursename);
}
