package com.novopay.ewalletservice.model;
import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
@Data
public class CalculateChargeCommissionRequestWO {
    @DecimalMin(value = "0.0", inclusive = false,message = "amount must be greater than 0.0 ")
    @Digits(integer=10, fraction=2)
    private BigDecimal amount;
    private boolean commissionApplicable;
    private boolean chargeApplicable;

}
