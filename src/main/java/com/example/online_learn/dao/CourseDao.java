package com.example.online_learn.dao;

import com.example.online_learn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseDao extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {
    Course findByCourseName(String coursename);
}
