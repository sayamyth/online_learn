package com.example.online_learn.controller;

import com.example.online_learn.entity.CourseWareType;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.service.CourseWareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WareTypeController {
    @Autowired
    private CourseWareTypeService courseWareTypeService;

    @RequestMapping("/toWareTypeList")
    public String toWareTypeList(){
        return "wareType/wareType-list";
    }
    @RequestMapping("/toWareTypeAdd")
    public String toWareTypeAdd(){
        return "wareType/wareType-add";
    }
    @RequestMapping("/toWareTypeUpdate")
    public String toWareTypeUpdate(){
        return "wareType/wareType-update";
    }


    @RequestMapping("/getWareTypeList")
    @ResponseBody
    public List getWareTypeList(){
        return courseWareTypeService.findCourseWareType();
    }

    @RequestMapping("/addWareType")
    @ResponseBody
    public Msg addWareType(String name){
        CourseWareType courseWareType = new CourseWareType();
        courseWareType.setCourseWareTypeName(name);

        CourseWareType saveCourseWareType = courseWareTypeService.saveCourseWareType(courseWareType);
        if (saveCourseWareType!=null){
            return Msg.success().add("msg","添加成功");
        }else {
            return Msg.success().add("msg","添加失败");
        }
    }

    @RequestMapping("/updateWareType")
    @ResponseBody
    public Msg updateWareType(Integer id,String name){
        CourseWareType courseWareType = new CourseWareType();
        courseWareType.setCourseWareTypeId(id);
        courseWareType.setCourseWareTypeName(name);

        CourseWareType saveCourseWareType = courseWareTypeService.saveCourseWareType(courseWareType);
        if (saveCourseWareType!=null){
            return Msg.success().add("msg","修改成功");
        }else {
            return Msg.success().add("msg","修改失败");
        }
    }

    @RequestMapping("/deleteWareType")
    @ResponseBody
    public Msg deleteWareType(String id){
        int i = courseWareTypeService.deleteCourseWareType(id);
        if (i>0){
            return Msg.success().add("msg","删除成功");
        }else {
            return Msg.success().add("msg","删除失败");
        }
    }
}
