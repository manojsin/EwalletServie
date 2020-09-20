package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyResponse {
    private Long toAccountNo;
    private Long formAccountNo;
    private BigDecimal transferAmount;
    private BigDecimal availableBal;
    private SuccessResponse success;
    public TransferMoneyResponse(TransferMoneyRequestWO walletDTO,BigDecimal aval) {
        this.toAccountNo=walletDTO.getToAccountNo();
        this.formAccountNo=walletDTO.getFormAccountNo();
        this.transferAmount=walletDTO.getAmount();
        this.availableBal =aval;
        this.success =getSuccessRespon();
    }
    private SuccessResponse getSuccessRespon() {
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setStatus(true);
        successResponse.setSuccessMsg("Account has been debited!");
        return successResponse;
    }
}
