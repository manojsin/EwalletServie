package com.novopay.ewalletservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserAccountResponse {
    private Long accountId;
    private ErrorResponse error;
    private SuccessResponse success;
}
