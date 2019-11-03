package com.example.online_learn.service.imp;

import com.example.online_learn.dao.ClassDao;
import com.example.online_learn.entity.Klass;
import com.example.online_learn.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassImp implements ClassService {
    @Autowired
    private ClassDao classDao;

    @Override
    public Klass findClassByName(String classname) {
        return classDao.findByClassName(classname);
    }

    @Override
    public List<Klass> getClassList() {
        return classDao.findAll();
    }

    @Override
    public Page<Klass> getClassListByNameWithPage(Specification specification, PageRequest request) {
        return classDao.findAll(specification,request);
    }

    @Override
    public Page<Klass> getClassListWithPage(PageRequest request) {
        return classDao.findAll(request);
    }

    @Override
    public Klass saveClass(Klass klass) {
        return classDao.save(klass);
    }

    @Override
    public int deleteClass(String classid) {
        try {
            classDao.deleteById(Long.valueOf(classid));
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
