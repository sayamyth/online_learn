package com.example.online_learn.service.imp;

import com.example.online_learn.dao.CourseWareTypeDao;
import com.example.online_learn.entity.CourseWareType;
import com.example.online_learn.service.CourseWareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseWareTypeImp implements CourseWareTypeService {

    @Autowired
    private CourseWareTypeDao courseWareTypeDao;

    @Override
    public List<CourseWareType> findCourseWareType() {
        return courseWareTypeDao.findAll();
    }

    @Override
    public CourseWareType saveCourseWareType(CourseWareType courseWareType) {
        return courseWareTypeDao.save(courseWareType);
    }

    @Override
    public int deleteCourseWareType(String id) {
        try {
            courseWareTypeDao.deleteById(Long.valueOf(id));
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }
}
