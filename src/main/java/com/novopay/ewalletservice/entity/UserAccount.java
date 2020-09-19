package com.novopay.ewalletservice.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String userName;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();
}
