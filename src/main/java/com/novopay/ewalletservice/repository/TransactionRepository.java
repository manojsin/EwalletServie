package com.novopay.ewalletservice.repository;

import com.novopay.ewalletservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true, value = "select * from transaction where transaction_reference = ?")
    Optional<Transaction> getTransactionByRef(Long txnRef);
    @Query(nativeQuery = true, value = "select * from transaction where account_no = ?")
    List<Transaction> getTransactionsForUser(Long accountId);
}
