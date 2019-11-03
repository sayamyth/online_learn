package com.example.online_learn.service;

import com.example.online_learn.entity.CourseWare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;


public interface CourseWareService {
    /**
     * 添加课件,修改课件
     */
    CourseWare saveCourseWare(CourseWare courseWare);

    /**
     * 展示课件，同时也可以进行模糊查询
     */
    Page getCourseWareListWithPage(Specification specification, PageRequest pageRequest);

    /**
     * 通过id删除课件
     */
    int deleteCourseWare(String id);

    /**
     * 通过课件名称精确找到课件
     */

    CourseWare findOneCourseWare(String name);

    CourseWare findOneById(String id);
}
