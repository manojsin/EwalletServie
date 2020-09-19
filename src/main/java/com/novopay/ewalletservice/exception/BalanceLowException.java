package com.novopay.ewalletservice.exception;
import lombok.Data;
@Data
public class BalanceLowException extends RuntimeException {
    private String message;
    private int errorCode;
    public BalanceLowException(String message , int errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }
}
