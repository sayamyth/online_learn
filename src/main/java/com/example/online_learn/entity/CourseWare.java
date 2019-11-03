package com.example.online_learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
public class CourseWare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_ware_id")
    private long courseWareId;
//    @Column(name = "course_ware_name")
//    private String courseWareName;
    @Column(name = "course_ware_path")
    private String courseWarePath;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "suffix")
    private String suffix;

    //外键
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    @NotFound(action= NotFoundAction.IGNORE)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_ware_type_id")
    @JsonBackReference
    @NotFound(action= NotFoundAction.IGNORE)
    private CourseWareType courseWareType;

    @Transient
    private String CourseWareTypeId;
    @Transient
    private String courseWareTypeName;
    @Transient
    private String courseName;
    @Transient
    private String courseId;

    public String getCourseWareTypeName() {
        return courseWareTypeName;
    }

    public void setCourseWareTypeName(String courseWareTypeName) {
        this.courseWareTypeName = courseWareTypeName;
    }

    public long getCourseWareId() {
        return courseWareId;
    }

    public void setCourseWareId(long courseWareId) {
        this.courseWareId = courseWareId;
    }

    public String getCourseWarePath() {
        return courseWarePath;
    }

    public void setCourseWarePath(String courseWarePath) {
        this.courseWarePath = courseWarePath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseWareType getCourseWareType() {
        return courseWareType;
    }

    public void setCourseWareType(CourseWareType courseWareType) {
        this.courseWareType = courseWareType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseWareTypeId() {
        return CourseWareTypeId;
    }

    public void setCourseWareTypeId(String courseWareTypeId) {
        CourseWareTypeId = courseWareTypeId;
    }
}
