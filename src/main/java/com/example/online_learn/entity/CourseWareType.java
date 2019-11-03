package com.example.online_learn.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CourseWareType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_ware_type_id")
    private long courseWareTypeId;

    @Column(name = "course_ware_type_name")
    private String courseWareTypeName;

    @OneToMany(mappedBy = "courseWareType")
    List<CourseWare> courseWare = new ArrayList<>();

    public long getCourseWareTypeId() {
        return courseWareTypeId;
    }

    public void setCourseWareTypeId(long courseWareTypeId) {
        this.courseWareTypeId = courseWareTypeId;
    }

    public String getCourseWareTypeName() {
        return courseWareTypeName;
    }

    public void setCourseWareTypeName(String courseWareTypeName) {
        this.courseWareTypeName = courseWareTypeName;
    }

    public List<CourseWare> getCourseWare() {
        return courseWare;
    }

    public void setCourseWare(List<CourseWare> courseWare) {
        this.courseWare = courseWare;
    }
}
