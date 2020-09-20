package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculateChargeCommissionRequestWO {
    private BigDecimal amount;
    private boolean commissionApplicable;
    private boolean chargeApplicable;

}
