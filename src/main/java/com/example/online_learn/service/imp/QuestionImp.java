package com.example.online_learn.service.imp;

import com.example.online_learn.dao.QuestionDao;
import com.example.online_learn.entity.Question;
import com.example.online_learn.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class QuestionImp implements QuestionService {
    @Autowired
    private QuestionDao questionDao;


    @Override
    public Question saveQuestion(Question question) {
        return questionDao.save(question);
    }

    @Override
    public int deleteQuestion(String id) {
        try {
            questionDao.deleteById(Long.valueOf(id));
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Page<Question> getQuestionListWithPage(Specification specification, PageRequest pageRequest) {
        return questionDao.findAll(specification,pageRequest);
    }

    @Override
    public Question finQuestionOne(String name, String courseid) {
        return questionDao.findOne(name,courseid);
    }

    @Override
    public Question test(Long id){
        return questionDao.getOne(id);
    }
}
