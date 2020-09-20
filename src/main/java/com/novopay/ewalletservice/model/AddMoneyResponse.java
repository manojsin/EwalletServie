package com.novopay.ewalletservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddMoneyResponse {
    private Long transactionId;
    private ErrorResponse error;
    private SuccessResponse success;
}
