package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.*;
public interface TransactionService {
    UserAccount transactionsByUserAccountID(Long accountId) ;
    AddMoneyResponse createTransaction(TransactionRequestWO requestWO);
    TransferMoneyResponse transfer(TransferMoneyRequestWO walletDTO);
    CalculateChargeCommissionResponse calculateChargeCommission(CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO);
   //todo add atoher feature
    /*Transaction transactionByRef(Long txnRef);
    BigDecimal balanceByUserAccountID(Long accountId) ;
    List<Transaction> transactions();
    Transaction save(Transaction t);
    Transaction update(Transaction t, Long id);
    List<Transaction> getList();*/
}
