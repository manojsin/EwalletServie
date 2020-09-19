package com.novopay.ewalletservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionData {
    private String status;
    private String errorMessage;
    private Map<String,String> errorMap;
    private int errorCode;

    public ExceptionData(String errorMessage , int erroCode){
        //this.status = CommonConstant.FAILURE;
        this.errorMessage = errorMessage;
        this.errorCode = erroCode;
    }

    public ExceptionData(String invalidRequestValue, int invalidRequestCode, Map<String, String> errorMap) {
        this(invalidRequestValue,invalidRequestCode);
        this.errorMap = errorMap;
    }
}
