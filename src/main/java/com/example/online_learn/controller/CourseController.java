package com.example.online_learn.controller;

import com.example.online_learn.entity.Course;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 添加新的课程,此处修改和添加使用一个接口
     * @param coursename
     * @return
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public Msg addCourse(String coursename){
        //判断是否有此课程
        Course byName = courseService.findOneByName(coursename);
        if (byName!=null){
            return Msg.success().add("msg","已有该课程");
        }
        //执行添加操作
        Course course = new Course();
        course.setCourseName(coursename);
        Course saveCourse = courseService.saveCourse(course);
        if (saveCourse!=null){
            return Msg.success().add("msg","添加课程成功");
        }else {
            return Msg.success().add("msg","添加课程失败");
        }
    }
    @RequestMapping("/updateCourse")
    @ResponseBody
    public Msg updateCourse(Integer courseid,String coursename){
        //判断是否有此课程
        Course byName = courseService.findOneByName(coursename);
        if (byName!=null){
            return Msg.success().add("msg","已有该课程");
        }
        //执行添加操作
        Course course = new Course();
        course.setCourseId(courseid);
        course.setCourseName(coursename);
        Course saveCourse = courseService.saveCourse(course);
        if (saveCourse!=null){
            return Msg.success().add("msg","修改课程成功");
        }else {
            return Msg.success().add("msg","修改课程失败");
        }
    }
    /**
     * 得到课程列表，不带分页
     */
    @RequestMapping("/getCourseList")
    @ResponseBody
    public List getCourseList(){
        return courseService.getCourseList();
    }

    /**
     * 得到课程列表，带分页
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getCourseListWithPage")
    @ResponseBody
    public Page getCourseListWithPage(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("courseId")));
        Page list = courseService.getCourseListWithPage(pageRequest);
        return list;
    }

    /**
     * 模糊查询得到课程列表，带分页
     * @param coursename
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getCourseListByNameWithPage")
    @ResponseBody
    public Page getCourseListByNameWithPage(String coursename,Integer page, Integer limit){
        if (page==null || limit==null ){
            page=0;
            limit=10;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("courseId")));
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("courseName");
                Predicate like = criteriaBuilder.like(path, "%" + coursename + "%");
                return like;
            }
        };
        Page list = courseService.getCourseListByNameWithPage(spec,pageRequest);
        return list;
    }

    /**
     * 通过id删除课程
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Msg deleteCourse(String id){
        String ids[];
        ids = id.split(",");
        int success=0;
        int fail=0;

        for (String s : ids) {
            int i = courseService.deleteCourse(s);
            if (i>0){
                success++;
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+ids.length+"个，成功"+success+"个，失败"+fail+"个");
    }
}
