package com.example.online_learn.controller;

import com.example.online_learn.entity.Course;
import com.example.online_learn.entity.Klass;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.entity.Question;
import com.example.online_learn.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    /**
     * 新增试题
     */
    @RequestMapping("addQuestion")
    @ResponseBody
    public Msg addQuestion(String name,String a,String b,String c,String d,String answer,String courseid){
        Course course = new Course();
        course.setCourseId(Long.valueOf(courseid));

        Question question = new Question();
        question.setCourse(course);
        question.setQuestionName(name);
        question.setOptionA(a);
        question.setOptionB(b);
        question.setOptionC(c);
        question.setOptionD(d);
        question.setIsBegin("0");
        question.setAnswer(answer);
        //判断此次是否存在
        Question one = questionService.finQuestionOne(name, courseid);
        if (one!=null){
            return Msg.success().add("msg","此题已存在");
        }
        Question saveQuestion = questionService.saveQuestion(question);
        if (saveQuestion!=null){
            return Msg.success().add("msg","添加试题成功");
        }else {
            return Msg.success().add("msg","添加试题失败");
        }
    }
    /**
     * 修改试题
     */
    @RequestMapping("updateQuestion")
    @ResponseBody
    public Msg updateQuestion(long id,String name, String a, String b, String c, String d, String answer, String courseid){
        Course course = new Course();
        course.setCourseId(Long.valueOf(courseid));

        Question question = new Question();
        question.setCourse(course);
        question.setQuestionName(name);
        question.setQuestionId(id);
        question.setOptionA(a);
        question.setOptionB(b);
        question.setOptionC(c);
        question.setOptionD(d);
        question.setIsBegin("0");
        question.setAnswer(answer);
        //判断此次是否存在
        Question one = questionService.finQuestionOne(name, courseid);
        if (one!=null){
            return Msg.success().add("msg","此题已存在");
        }
        Question saveQuestion = questionService.saveQuestion(question);
        if (saveQuestion!=null){
            return Msg.success().add("msg","修改试题成功");
        }else {
            return Msg.success().add("msg","修改试题失败");
        }
    }
    /**
     * 查询试题列表
     */
    @RequestMapping("getQuestionListWithPage")
    @ResponseBody
    public Page getQuestionListWithPage(Integer page,Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("questionId")));
        Page<Question> list = questionService.getQuestionListWithPage(null, pageRequest);
        for (Question question : list) {
            question.setCourseId(Long.toString(question.getCourse().getCourseId()));
            question.setCourseName(question.getCourse().getCourseName());
        }
        return list;
    }
    @RequestMapping("getQuestionListByNameWithPage")
    @ResponseBody
    public Page getQuestionListByNameWithPage(String name,Integer page,Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("questionId")));
        Specification<Question> spec = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("questionName");
                Predicate like = criteriaBuilder.like(path.as(String.class), "%" + name + "%");
                return like;
            }
        };
        Page<Question> list = questionService.getQuestionListWithPage(spec, pageRequest);
        for (Question question : list) {
            question.setCourseId(Long.toString(question.getCourse().getCourseId()));
            question.setCourseName(question.getCourse().getCourseName());
        }
        return list;
    }
    /**
     * 删除试题
     */
    @RequestMapping("deleteQuestion")
    @ResponseBody
    public Msg deleteQuestion(String id){
        String ids[];
        ids = id.split(",");
        int success=0;
        int fail=0;
        for (String s : ids) {
            int i = questionService.deleteQuestion(s);
            if (i>0){
                success++;
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+ids.length+"个，成功"+success+"个，失败"+fail+"个");
    }
    @RequestMapping("/isBegin")
    @ResponseBody
    public Msg isBegin(long id,String num){

        Question question = questionService.test(id);
        question.setIsBegin(num);
        questionService.saveQuestion(question);
        return null;
    }
}
