package com.example.online_learn.controller;

import com.example.online_learn.entity.Msg;
import com.example.online_learn.entity.Type;
import com.example.online_learn.entity.User;
import com.example.online_learn.service.TypeService;
import com.example.online_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 获取权限列表
     */
    @RequestMapping("/getTypeList")
    @ResponseBody
    public List getTypeList() {
        return typeService.getType();
    }

    /**
     * 获取权限列表.带上分页
     */
    @RequestMapping("/getTypeListWithPage")
    @ResponseBody
    public Page getTypeList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("typeId")));
        Page<Type> type = typeService.getTypeWithPage(pageRequest);
        return type;
    }

    /**
     * 新增权限
     */
    @RequestMapping("/addType")
    @ResponseBody
    public Msg addType(String typename) {
        Type exist = typeService.getTypeByName(typename);
        if (exist != null) {
            return Msg.success().add("msg", "已存在该权限");
        }
        Type type = new Type();
        type.setTypeName(typename);
        Type type1 = typeService.saveType(type);
        if (type1 != null) {
            return Msg.success().add("msg", "添加权限成功");
        } else {
            return Msg.success().add("msg", "添加权限失败");
        }
    }

    /**
     * 修改权限名称
     */

    @RequestMapping("/updateType")
    @ResponseBody
    public Msg updateType(String typeid,String typename){
        Type exist = typeService.getTypeByName(typename);
        if (exist != null) {
            return Msg.success().add("msg", "已存在该权限");
        }
        Type type = new Type();
        type.setTypeId(Long.valueOf(typeid));
        type.setTypeName(typename);
        Type type1 = typeService.saveType(type);
        if (type1 != null) {
            return Msg.success().add("msg", "修改权限成功");
        } else {
            return Msg.success().add("msg", "修改权限失败");
        }
    }

    /**
     * 根据id删除权限
     */
    @RequestMapping("deleteType")
    @ResponseBody
    public Msg deleteType(String typeid,String typename) {
        //检测是否可以删除此权限
        Type typeByName = typeService.getTypeByName(typename);

        if (!typeByName.getUsers().isEmpty()){
            return Msg.success().add("msg", "有用户占用此权限，无法删除");
        }
        int i = typeService.deleteTypeById(typeid);
        if (i > 0) {
            return Msg.success().add("msg", "权限删除成功");
        }else {
            return Msg.success().add("msg", "权限删除失败");
        }
    }
}
