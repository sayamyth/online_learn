package com.example.online_learn.controller;

import com.example.online_learn.dao.ClassDao;
import com.example.online_learn.entity.Klass;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.service.ClassService;
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
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 添加新的班级
     */
    @RequestMapping("/addClass")
    @ResponseBody
    public Msg addClass(String classname){
        Klass byName = classService.findClassByName(classname);
        if (byName!=null){
            return Msg.success().add("msg","该班级已存在");
        }
        Klass klass = new Klass();
        klass.setClassName(classname);
        Klass saveClass = classService.saveClass(klass);
        if (saveClass!=null){
            return Msg.success().add("msg","添加班级成功");
        }else {
            return Msg.success().add("msg","添加班级失败");
        }
    }
    /**
     * 修改班级
     */
    @RequestMapping("/updateClass")
    @ResponseBody
    public Msg updateClass(String classid,String classname){
        Klass byName = classService.findClassByName(classname);
        if (byName!=null){
            return Msg.success().add("msg","该班级已存在");
        }
        Klass klass = new Klass();
        klass.setClassId(Long.valueOf(classid));
        klass.setClassName(classname);
        Klass saveClass = classService.saveClass(klass);
        if (saveClass!=null){
            return Msg.success().add("msg","修改班级成功");
        }else {
            return Msg.success().add("msg","修改班级失败");
        }
    }

    /**
     *根据id删除班级
     */
    @RequestMapping("/deleteClass")
    @ResponseBody
    public Msg deleteClass(String id) {
        String ids[];
        int success=0;
        int fail=0;
        ids = id.split(",");
        for (String s : ids) {
            int i = classService.deleteClass(s);
            if (i>0){
                success++;
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+ids.length+"个，成功"+success+"个，失败"+fail+"个");
    }

    /**
     * 得到班级列表，不带分页
     */
    @RequestMapping("/getClassList")
    @ResponseBody
    public List getClassList(){

        return classService.getClassList();
    }

    /**
     * 根据班级名称模糊查询，带分页
     */
    @RequestMapping("/getClassListByNameLikePage")
    @ResponseBody
    public Page getClassListByNameLikePage(String classname,Integer page,Integer limit){
        if (page==null || limit==null ){
            page=0;
            limit=10;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, limit,Sort.by(Sort.Order.desc("classId")));

        Specification<Klass> spec = new Specification<Klass>() {
            @Override
            public Predicate toPredicate(Root<Klass> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("className");
                Predicate like = criteriaBuilder.like(path.as(String.class), "%" + classname + "%");
                return like;
            }
        };
        Page<Klass> list = classService.getClassListByNameWithPage(spec, pageRequest);
        return list;
    }

    /**
     *带分页的班级列表
     */
    @RequestMapping("/getClassListWithPage")
    @ResponseBody
    public Page getClassListWithPage(Integer page,Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit,Sort.by(Sort.Order.desc("classId")));
        Page<Klass> list = classService.getClassListWithPage(pageRequest);
        return list;
    }

}
