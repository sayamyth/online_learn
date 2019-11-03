package com.example.online_learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.*;

@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "question_name")
    private String questionName;

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;
    @Column(name = "option_c")
    private String optionC;
    @Column(name = "option_d")
    private String optionD;
    //正确选
    @Column(name = "answer")
    private String answer;

    @Column(name ="is_begin")
    private String isBegin;

    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    //显示字段
    @Transient
    private String courseName;

    @Transient
    private String courseId;

    public String getIsBegin() {
        return isBegin;
    }

    public void setIsBegin(String isBegin) {
        this.isBegin = isBegin;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionName='" + questionName + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                ", isBegin='" + isBegin + '\'' +
                ", course=" + course +
                ", courseName='" + courseName + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
