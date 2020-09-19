package com.novopay.ewalletservice.repository;

import com.novopay.ewalletservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true, value = "select * from transaction where transaction_reference = ?")
    Optional<Transaction> getTransactionByRef(Long txnRef);
    @Query(nativeQuery = true, value = "select ifnull(sum(amount),0.00) from transaction where user_account_id = ?")
    BigDecimal getBalance(Long accountId);
    @Query(nativeQuery = true, value = "select * from transaction where user_account_id = ?")
    List<Transaction> getTransactionsForUser(Long accountId);
}
