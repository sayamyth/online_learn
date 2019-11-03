package com.example.online_learn.dao;

import com.example.online_learn.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Question> {
    @Query("from Question where questionName=?1 and course_id=?2")
    Question findOne(String name,String courseid);

}
