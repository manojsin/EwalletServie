package com.novopay.ewalletservice.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CommonUtil {
    //Validate Request
    public static void validateRequest(BindingResult errors){
        // Check validation errors
        if (errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            Map<String,String> errorMap = new TreeMap<>();
            for (int i=0 ; i<errorList.size() ; i++) {
                errorMap.put("Parameter:"+i,errorList.get(i).getDefaultMessage());
            }

            throw new RequestInvalidException(errorMap);
        }
    }
}
