package com.example.javaSurvey.survey;

import com.example.javaSurvey.surveyAnswer.SurveyAnswer;
import com.example.javaSurvey.user.User;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User surveyOwner;

    @NotNull
    private String question;
    @OneToMany(mappedBy = "survey")
    private List<SurveyAnswer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSurveyOwner() {
        return surveyOwner;
    }

    public void setSurveyOwner(User surveyOwner) {
        this.surveyOwner = surveyOwner;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<SurveyAnswer> answers) {
        this.answers = answers;
    }

    public List<SurveyAnswer> getAnswers() {
        return answers;
    }



}
