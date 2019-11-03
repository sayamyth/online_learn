package com.example.online_learn.dao;

import com.example.online_learn.entity.CourseWareType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseWareTypeDao extends JpaRepository<CourseWareType,Long>, JpaSpecificationExecutor<CourseWareType> {
}
