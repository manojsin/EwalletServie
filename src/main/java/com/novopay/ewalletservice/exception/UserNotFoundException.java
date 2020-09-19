package com.novopay.ewalletservice.exception;
import lombok.Data;
@Data
public class UserNotFoundException extends RuntimeException {
    private String message;
    private int errorCode;

    public UserNotFoundException(String message , int errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }
}
