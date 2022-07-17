package com.example.javaSurvey.surveyAnswer;

import com.example.javaSurvey.survey.Survey;
import com.example.javaSurvey.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "surveyanswers")
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Survey.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "survey_id",referencedColumnName = "id", nullable = false)
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User answerUser;

    @NotNull
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public User getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(User answerUser) {
        this.answerUser = answerUser;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
