package com.example.online_learn.service;

import com.example.online_learn.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface QuestionService {
    //添加试题,修改试题
    Question saveQuestion(Question question);
    //删除是试题
    int deleteQuestion(String id);
    //得到试题带分页
    Page<Question> getQuestionListWithPage(Specification specification, PageRequest pageRequest);
    //通过name和classid得到唯一一个试题
    Question finQuestionOne(String name,String courseid);
    public Question test(Long id);
}
