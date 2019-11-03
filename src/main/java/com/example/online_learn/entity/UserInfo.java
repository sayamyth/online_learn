package com.example.online_learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table
public class UserInfo {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private long userInfoId;
    //真实姓名
    @Column(name = "user_info_name")
    private String userInfoName;
    //外键关联class表
    @NotFound(action=NotFoundAction.IGNORE)//没有对应外键是也可以显示
    @ManyToOne(targetEntity = Klass.class)
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private Klass klass;
    //外键关联user表
    @NotFound(action= NotFoundAction.IGNORE)
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

//为展示数据的字段
    @Transient
    private String classId;
    @Transient
    private String userId;
    @Transient
    private String className;
    @Transient
    private String userName;
    public long getUserInfoId() {
        return userInfoId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
