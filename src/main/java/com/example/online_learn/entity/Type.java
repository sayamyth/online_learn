package com.example.online_learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Type {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long typeId;

    //权限名称详情
    @Column(name = "type_name")
    private String typeName;

    @OneToMany(mappedBy = "type")
    private List<User> users= new ArrayList<>();


    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", users=" + users +
                '}';
    }
}
