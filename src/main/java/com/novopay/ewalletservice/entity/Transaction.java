package com.novopay.ewalletservice.entity;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String details;
    private BigDecimal amount;
    private Long accountNo;
    private Date transactionDate=new Date();
    private Long transactionReference;
    private Long transactionAccountNo;
}
