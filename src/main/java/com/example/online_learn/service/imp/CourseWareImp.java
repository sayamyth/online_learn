package com.example.online_learn.service.imp;

import com.example.online_learn.dao.CourseDao;
import com.example.online_learn.dao.CourseWareDao;
import com.example.online_learn.entity.CourseWare;
import com.example.online_learn.service.CourseWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class CourseWareImp implements CourseWareService {
    @Autowired
    private CourseWareDao courseWareDao;

    @Override
    public CourseWare saveCourseWare(CourseWare courseWare) {
        return courseWareDao.save(courseWare);
    }

    @Override
    public Page getCourseWareListWithPage(Specification specification, PageRequest pageRequest) {
        return courseWareDao.findAll(specification,pageRequest);
    }

    @Override
    public int deleteCourseWare(String id) {
        try {
            courseWareDao.deleteById(Long.valueOf(id));
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public CourseWare findOneCourseWare(String name) {
        return courseWareDao.findByPrefix(name);
    }

    @Override
    public CourseWare findOneById(String id) {
        return courseWareDao.getOne(Long.valueOf(id));
    }


}
