package com.novopay.ewalletservice.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(Exception e){
           return new ResponseEntity<>("ff", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
