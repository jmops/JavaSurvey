package com.example.javaSurvey.surveyAnswer;

import com.example.javaSurvey.constant.Const;
import com.example.javaSurvey.survey.Survey;
import com.example.javaSurvey.survey.SurveyService;
import com.example.javaSurvey.user.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
public class SurveyAnswerController {
    @Autowired
    SurveyService surveyService;
    @Autowired
    SurveyAnswerService surveyAnswerService;
    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @RequestMapping(value = "/{surveyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> answerSurvey(@PathVariable("surveyId") Long surveyId, @RequestBody SurveyAnswerDto answer){
        var survey = surveyService.findOne(surveyId);
        SurveyAnswer answerEntity;
        if(survey != null){
            answerEntity = modelMapper.map(answer, SurveyAnswer.class);
            answerEntity.setAnswerUser(userService.findOne(1l));
            answerEntity.setSurvey(survey);
            surveyAnswerService.insertOne(answerEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

/*
        answer.setAnswerUser(userService.findOne(1l)); // could be null @TODO
        answer.setSurvey(surveyService.findOne(surveyId)); // The path variable overrides the survey id in the body, if it is set.
        if(answer.getSurvey() != null){ // Survey exists
            if (answer.getAnswer() != null && answer.getId() == null) {
                if(answer.getAnswer().length() >= Const.MINANSWERLENGTH && answer.getAnswer().length() <= Const.MAXANSWERLENGTH){
                    surveyAnswerService.insertOne(answer);
                    return new ResponseEntity<>(HttpStatus.OK);

                } else return new ResponseEntity<>("Invalid answer length", HttpStatus.BAD_REQUEST);
            }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("Survey not found", HttpStatus.NOT_FOUND);*/
    }

    @RequestMapping(value = "/all/{surveyId}", method = RequestMethod.GET)
    public ResponseEntity<List<SurveyAnswerDto>> getSurveyAnswers(@PathVariable("surveyId") Long surveyId){
        var survey = surveyService.findOne(surveyId);
        List<SurveyAnswerDto> answers;
        if(survey != null){
            answers = (List<SurveyAnswerDto>) survey.getAnswers()
                    .stream()
                    .map(surveyAnswer ->{
                       var mappedDto = modelMapper.map(surveyAnswer, SurveyAnswerDto.class);
                       mappedDto.setNameOfUser(surveyAnswer.getAnswerUser().getName()); // Only the name should be available
                       return mappedDto;
        }).collect(Collectors.toList());
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        /*
        var gson = new Gson();
        var survey = surveyService.findOne(surveyId);
        if(survey != null){
            return new ResponseEntity<>(survey.getAnswers(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
    }

}
