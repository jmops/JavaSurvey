package com.example.javaSurvey.survey;
import com.example.javaSurvey.constant.Const;
import com.example.javaSurvey.exception.InvalidQuestionException;
import com.example.javaSurvey.user.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    SurveyService surveyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<List<SurveyDto>> getAllSurveys(){
        var surveys = surveyService.findAll()
                .stream()
                .map(survey -> modelMapper.map(survey, SurveyDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @RequestMapping(value = "/{surveyId}", method = RequestMethod.GET)
    /*
     * Handles GET requests for the /survey/{surveyId} endpoint.
     */
    public ResponseEntity<SurveyDto> getSurvey(@PathVariable("surveyId") Long surveyId){

        var survey = surveyService.findOne(surveyId);
        if (survey != null)
            return new ResponseEntity(modelMapper.map(survey, SurveyDto.class), HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    /*
     * Handles POST requests for the /survey/new endpoint.
     * Create a new survey.
     */
    public ResponseEntity<Long> newSurvey(@RequestBody SurveyDto surveyDto, HttpServletRequest request) throws InvalidQuestionException {
        var session = request.getSession(false);
        if(session != null){
            Survey survey = modelMapper.map(surveyDto, Survey.class);
            survey.setId(null); // Auto incremented
            survey.setSurveyOwner(userService.findOne((Long) session.getAttribute("ID")));
            survey = surveyService.insertOne(survey);
            return new ResponseEntity<>(survey.getId(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        //System.out.println(request.getSession().getAttribute("ID"));
    }
}
