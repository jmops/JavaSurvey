package com.example.javaSurvey.user;
import com.example.javaSurvey.exception.UserCreationException;
import com.example.javaSurvey.survey.SurveyDto;
import com.example.javaSurvey.survey.SurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;
    @Autowired
    SurveyService surveyService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newUser(@RequestBody NewUserDto newUserDto) throws UserCreationException {
        var user = modelMapper.map(newUserDto, User.class);
        //if(user)
        if(userService.findOneByName(user.getName()) == null){ // Doesn't exist
            userService.insertOne(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>("Name is taken", HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request){
        var user = userService.findOneByName(loginUserDto.getName());
        if(user != null && user.checkPassword(loginUserDto.getPassword())){ // Authenticated
            request.getSession(true).setAttribute("ID", user.getId()); // Using ID instead of User-reference for scalability. (in the case of multiple servers)
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logoutUser(HttpServletRequest request){
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/my-surveys", method = RequestMethod.GET)
    public ResponseEntity<List<SurveyDto>> getAllSurveys(HttpServletRequest request){
        var session = request.getSession(false); // Is the user authenticated
        List<SurveyDto> surveys;
        if (session != null){

            surveys = surveyService.findAllByUserId((Long) session.getAttribute("ID"))
                    .stream()
                    .map(survey -> modelMapper.map(survey, SurveyDto.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(surveys, HttpStatus.OK);
        } else  return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
