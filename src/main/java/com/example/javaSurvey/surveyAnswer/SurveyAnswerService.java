package com.example.javaSurvey.surveyAnswer;

import com.example.javaSurvey.survey.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyAnswerService {
    @Autowired private SurveyAnswerRepository surveyAnswerRepository;

    /**
     * Insert a new survey answer.
     * @param answer
     * @return id of the answer.
     */
    public Long insertOne(SurveyAnswer answer){
        var storedSurveyAnswer = surveyAnswerRepository.save(answer);
        return storedSurveyAnswer.getId();
    }
}
