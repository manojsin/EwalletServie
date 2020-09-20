package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculateChargeCommissionResponse {
    private BigDecimal baseAmount;
    private BigDecimal totalAmount;
    private ChargeDetail chargeDetail;
    private CommissionDetail commissionDetail;

    public CalculateChargeCommissionResponse(BigDecimal amount, BigDecimal chargeRate, BigDecimal commissionRate, BigDecimal chargeAmount, BigDecimal commissionAmount) {
        this.baseAmount=amount;
        this.chargeDetail=new ChargeDetail(chargeRate,chargeAmount);
        this.commissionDetail=new CommissionDetail(commissionRate,commissionAmount);

    }

    @Data
    public static class ChargeDetail{
        private BigDecimal chargeRate;
        private BigDecimal chargeAmount;

        public ChargeDetail(BigDecimal chargeRate, BigDecimal chargeAmount) {
            this.chargeRate=chargeRate;
            this.chargeAmount=chargeAmount;
        }
    }
    @Data
    public static class CommissionDetail{
        private BigDecimal commRate;
        private BigDecimal commAmount;

        public CommissionDetail(BigDecimal commissionRate, BigDecimal commissionAmount) {
            this.commRate=commissionRate;
            this.commAmount=commissionAmount;
        }
    }
}
