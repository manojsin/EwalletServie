package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.*;

import java.math.BigDecimal;
import java.util.List;
public interface TransactionService {
    Transaction save(Transaction t);
    Transaction update(Transaction t, Long id);
    List<Transaction> getList();
    List<Transaction> transactionsByUserAccountID(Long accountId) ;
    Transaction transactionByRef(Long txnRef);
    BigDecimal balanceByUserAccountID(Long accountId) ;
    List<Transaction> transactions();
    AddMoneyResponse createTransaction(TransactionRequestWO requestWO);
    TransferMoneyResponse transfer(TransferMoneyRequestWO walletDTO);
    CalculateChargeCommissionResponse calculateChargeCommission(CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO);
}
