package com.example.springwebmvctemplate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice // share across multiple controller -> global handling
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleIOException(IOException ex){
        ex.printStackTrace();
        if(ex instanceof FileNotFoundException){
            String detail = "File named " + ex.getMessage() + " is not found";
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
            problemDetail.setProperty("errorCode","1004");

            return problemDetail;
        }
        return  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler // Exception except above
    public ProblemDetail handleAllOther(Exception ex){
        ex.printStackTrace();
        return  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
