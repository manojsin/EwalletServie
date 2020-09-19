package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.TransactionRequestWO;
import com.novopay.ewalletservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
@Service
public class TransactionServiceImp implements TransactionService {

    private TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImp(TransactionRepository transactionRepository)
    {
        this.transactionRepository=transactionRepository;
    }

    @Override
    public Transaction save(Transaction t) {
        return null;
    }

    @Override
    public Transaction update(Transaction t, Long id) {
        return null;
    }

    @Override
    public List<Transaction> getList() {
        return null;
    }

    @Override
    public List<Transaction> transactionsByUserAccountID(Long accountId) {
        return null;
    }

    @Override
    public Transaction transactionByRef(Long txnRef) throws Exception {
        return null;
    }

    @Override
    public BigDecimal balanceByUserAccountID(Long accountId) {
        return null;
    }

    @Override
    public List<Transaction> transactions() {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction txn) {
        return null;
    }

    @Override
    public List<Transaction> transfer(TransactionRequestWO walletDTO, Long toUserAccountId, Long fromUserAccountId) {
        return null;
    }
}
