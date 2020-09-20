package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyRequestWO {
    private Long toAccountNo;
    private Long formAccountNo;
    private BigDecimal amount;
    private boolean isChargeApplicable;
    private boolean isCommissionApplicable;

}
