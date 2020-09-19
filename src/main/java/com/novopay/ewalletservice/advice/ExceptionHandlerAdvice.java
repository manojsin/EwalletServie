package com.novopay.ewalletservice.advice;
import com.novopay.ewalletservice.exception.BalanceLowException;
import com.novopay.ewalletservice.exception.ExceptionData;
import com.novopay.ewalletservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(Exception e){
        ExceptionData exceptionPojo = new ExceptionData("Internal Server Error", 500);
        return new ResponseEntity<>(exceptionPojo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BalanceLowException.class)
    public final ResponseEntity<ExceptionData> handleRequestInvalidException(BalanceLowException e){
        ExceptionData exceptionData = new ExceptionData(e.getMessage(),e.getErrorCode());
        return new ResponseEntity<>(exceptionData, HttpStatus.OK);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionData> handleRequestInvalidException(UserNotFoundException e){
        ExceptionData exceptionData = new ExceptionData(e.getMessage(),e.getErrorCode());
        return new ResponseEntity<>(exceptionData, HttpStatus.OK);
    }

}
