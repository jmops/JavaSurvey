package com.example.javaSurvey.survey;

import com.example.javaSurvey.surveyAnswer.SurveyAnswer;

import javax.persistence.Entity;
import java.util.List;

public class SurveyDto {
    private Long id;

    private String question;

    //private List<SurveyAnswer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
/*
    public List<SurveyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveyAnswer> answers) {
        this.answers = answers;
    }
*/



}
