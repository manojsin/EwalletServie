package com.novopay.ewalletservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TransactionRequestWO {
    private Long id;
    private Long userAccountId;
    private BigDecimal amount;
    private String details;
    private Date transactionDate;
    private Long transactionReference;
    public TransactionRequestWO() {
    }
   @Data
    public static class TransactionRequestBuilder {
        private Long id;
        private Long userAccountId;
        private BigDecimal amount;
        private String details;
        private Date transactionDate;
        private Long transactionReference;
    }
}
