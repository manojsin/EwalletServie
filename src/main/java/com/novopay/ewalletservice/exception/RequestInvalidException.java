package com.novopay.ewalletservice.exception;

import lombok.Data;

import java.util.Map;
@Data
public class RequestInvalidException extends RuntimeException {
    private String status;
    private Map<String,String> errorMap;

    public RequestInvalidException(Map<String,String> errorMap){
        this.status = "Failure";
        this.errorMap = errorMap;
    }

}
