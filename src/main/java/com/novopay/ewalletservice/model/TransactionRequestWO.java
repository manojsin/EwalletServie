package com.novopay.ewalletservice.model;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class TransactionRequestWO {
    private Long userAccountId;
    private BigDecimal amount;

}
