package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.TransactionRequestWO;
import java.math.BigDecimal;
import java.util.List;
public interface TransactionService {
    Transaction save(Transaction t);
    Transaction update(Transaction t, Long id);
    List<Transaction> getList();
    List<Transaction> transactionsByUserAccountID(Long accountId) ;
    Transaction transactionByRef(Long txnRef) throws Exception;
    BigDecimal balanceByUserAccountID(Long accountId) ;
    List<Transaction> transactions();
    Transaction createTransaction(Transaction txn);
    List<Transaction> transfer(TransactionRequestWO walletDTO, Long toUserAccountId, Long fromUserAccountId);
}
