package com.example.online_learn.service;

import com.example.online_learn.entity.Type;
import com.example.online_learn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TypeService {
    Type getTypeByName(String typename);

    //得到权限信息，不分页，用作数据展示
    List<Type> getType();

    //得到所有权限，带上分页
    Page<Type> getTypeWithPage(PageRequest pageRequest);

    //新增权限,修改权限
    Type saveType(Type type);

    //删除权限
    int deleteTypeById(String id);
}
