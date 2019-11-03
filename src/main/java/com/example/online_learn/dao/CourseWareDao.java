package com.example.online_learn.dao;

import com.example.online_learn.entity.Course;
import com.example.online_learn.entity.CourseWare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourseWareDao extends JpaRepository<CourseWare,Long>, JpaSpecificationExecutor<CourseWare> {
    CourseWare findByPrefix(String name);


}
