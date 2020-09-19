package com.novopay.ewalletservice.entity;

import com.sun.istack.NotNull;
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
    @ManyToOne
    private UserAccount userAccount;
    @NotNull
    private BigDecimal amount;
    private String details;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionDate;
    @NotNull
    private Long transactionReference;
    // @Version //for concurrency
    private int version;
}
