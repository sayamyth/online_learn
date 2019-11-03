package com.example.online_learn.service.imp;

import com.example.online_learn.dao.CourseDao;
import com.example.online_learn.entity.Course;
import com.example.online_learn.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseImp implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public Course saveCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> getCourseList() {
        return courseDao.findAll();
    }

    @Override
    public Page getCourseListWithPage(PageRequest pageRequest) {
        return courseDao.findAll(pageRequest);
    }

    @Override
    public Page getCourseListByNameWithPage(Specification specification, PageRequest pageRequest) {
        return courseDao.findAll(specification,pageRequest);
    }

    @Override
    public int deleteCourse(String courseid) {
        try {
            courseDao.deleteById(Long.valueOf(courseid));
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Course findOneByName(String coursename) {
        return courseDao.findByCourseName(coursename);
    }
}
