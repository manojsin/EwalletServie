package com.novopay.ewalletservice.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String successMsg;
    private boolean status;
}
