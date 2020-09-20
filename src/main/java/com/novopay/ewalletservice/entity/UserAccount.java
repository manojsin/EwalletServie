package com.novopay.ewalletservice.entity;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table
public class UserAccount {
    @Id
    @GeneratedValue
    private Long accountNo;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal availableBal;
    private String mobileNumber;
    private Date Created_on=new Date();
    @OneToMany(targetEntity= Transaction.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNo")
    private Set<Transaction> transactions = new HashSet<>();
}
