package com.example.online_learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klass {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long   classId;//班级id

    //班级名称
    @Column(name = "class_name")
    private String className;//班级名称

    @OneToMany(mappedBy = "klass")
    private List<UserInfo> userInfo = new ArrayList<>();





    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Klass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
