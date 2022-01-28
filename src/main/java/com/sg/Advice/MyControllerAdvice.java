package com.sg.Advice;

import com.sg.Exception.EmptyDogListException;
import com.sg.Exception.EmptyInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){

        return new ResponseEntity<String>("Input field is empty, Please look into it", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuch(NoSuchElementException noSuchElementException){

        return new ResponseEntity<String>("There is no Dog matching that Id", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyDogListException.class)
    public ResponseEntity<String> handleEmpty(EmptyDogListException emptyDogListException){

        return new ResponseEntity<String>("The List is empty, add a datapoint to it", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {                                                                                                          
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);

        return new ResponseEntity<Object>("Please change your http method type request", HttpStatus.NOT_FOUND);
    }
}
