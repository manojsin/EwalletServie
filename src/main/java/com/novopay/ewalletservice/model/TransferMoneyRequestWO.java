package com.novopay.ewalletservice.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferMoneyRequestWO {
    @NotNull(message = "toAccountNo can't be null!")
    private Long toAccountNo;
    @NotNull(message = "formAccountNo can't be null!")
    private Long formAccountNo;
    @DecimalMin(value = "0.0", inclusive = false,message = "amount must be greater than 0.0 ")
    @Digits(integer=10, fraction=2)
    private BigDecimal amount;
    private boolean isChargeApplicable;
    private boolean isCommissionApplicable;


}
