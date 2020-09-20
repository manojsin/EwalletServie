package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyResponse {
    private Long toAccountNo;
    private Long formAccountNo;
    private BigDecimal transferAmount;
    private BigDecimal availableAmount;
    private SuccessResponse successResponse;
    public TransferMoneyResponse(TransferMoneyRequestWO walletDTO,BigDecimal aval) {
        this.toAccountNo=walletDTO.getToAccountNo();
        this.formAccountNo=walletDTO.getFormAccountNo();
        this.transferAmount=walletDTO.getAmount();
        this.availableAmount=aval;
        this.successResponse=getSuccessRespon();
    }
    private SuccessResponse getSuccessRespon() {
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setStatus(true);
        successResponse.setSuccessMsg("Account has been debited!");
        return successResponse;
    }
}
