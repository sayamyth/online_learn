package com.example.online_learn.service;

import com.example.online_learn.entity.Klass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ClassService {

    //通过名字精确查询
    Klass findClassByName(String classname);

    //查询班级列表
    List<Klass> getClassList();

    //通过班级名模糊查询，带上分页
    Page<Klass> getClassListByNameWithPage(Specification specification,PageRequest request);

     // 查询班级列表，带上分页
    Page<Klass> getClassListWithPage(PageRequest request);


    // 新建班级，修改班级
    Klass saveClass(Klass klass);


     //删除班级
    int deleteClass(String classid);
}
