package com.example.javaSurvey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    /**
     * Handle exceptions from missing request parameters.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParamExc(MissingServletRequestParameterException ex) {
        return  new ResponseEntity(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * Handle exceptions from invalid HTTP methods.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleWrongHttpMethod(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidQuestionException.class)
    public ResponseEntity<String> handleInvalidQuestion(InvalidQuestionException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<String> handleUserCreationException(UserCreationException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



    /**
     * Handle server errors.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>  handleExceptions(Exception ex) {
        System.out.println(ex);
        /// @TODO log server errors
        return new ResponseEntity("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
