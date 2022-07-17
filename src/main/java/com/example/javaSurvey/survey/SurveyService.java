package com.example.javaSurvey.survey;


import com.example.javaSurvey.constant.Const;
import com.example.javaSurvey.exception.InvalidQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * buisness logic etc.
 */
@Service
/**
 * Handle all survey logic.
 */
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    /**
     *
     * @return all surveys.
     */
    public List<Survey> findAll(){
        var surveys = (List<Survey>) repository.findAll();
        return  surveys;
    }

    /**
     * Insert a new survey into the database.
     * @param survey
     */
    public Survey insertOne(Survey survey) throws InvalidQuestionException {
        var question = survey.getQuestion();
        if(question != null && question.length() >= Const.MINQUESTIONLENGTH                         // Question validation.
                && question.length() <= Const.MAXQUESTIONLENGTH                 //
                && question.endsWith("?")){
            return repository.save(survey);
        }else throw new InvalidQuestionException("Invalid question length. It needs to be between "
                + Const.MINQUESTIONLENGTH + " and " + Const.MAXQUESTIONLENGTH + " characters. The question also needs to end with a '?'");
    }

    /**
     * Find one survey with the given id.
     * @param id
     * @return survey with the given id
     */
    public Survey findOne(Long id){
        var optionalSurvey = repository.findById(id);
        return optionalSurvey.orElse(null);
    }

    public List<Survey> findAllByUserId(Long id){
        return repository.findAllByUserId(id);
    }
}
