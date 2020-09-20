package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.exception.BalanceLowException;
import com.novopay.ewalletservice.exception.UserNotFoundException;
import com.novopay.ewalletservice.model.*;
import com.novopay.ewalletservice.repository.TransactionRepository;
import com.novopay.ewalletservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImp implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserAccountService accountService;
    private UserAccountRepository userAccountRepository;
    @Autowired
    public TransactionServiceImp(TransactionRepository transactionRepository,
                                 UserAccountService accountService,
                                 UserAccountRepository userAccountRepository)
    {
        this.transactionRepository=transactionRepository;
        this.accountService=accountService;
        this.userAccountRepository=userAccountRepository;
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
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> transactionsByUserAccountID(Long accountId) {
        return transactionRepository.getTransactionsForUser(accountId);
    }

    @Override
    public Transaction transactionByRef(Long txnRef)  {
       return transactionRepository.getTransactionByRef(txnRef).orElseThrow(() -> new UserNotFoundException(String.format("transaction with ref '%d' doesnt exist", txnRef),404));
    }

    @Override
    public BigDecimal balanceByUserAccountID(Long accountId) {
        return transactionRepository.getBalance(accountId);
    }

    @Override
    public List<Transaction> transactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public AddMoneyResponse createTransaction(TransactionRequestWO transaction) {
        UserAccount userAccountDetails=userAccountRepository.findAllByAccountNo(transaction.getUserAccountId());
        Optional<BigDecimal> balanceCheck=Optional.ofNullable(userAccountDetails.getAmount());
        BigDecimal balance=balanceCheck.isPresent()? userAccountDetails.getAmount():new BigDecimal(0);
        BigDecimal availableBalance=balance.add(transaction.getAmount());
        if (availableBalance.compareTo(BigDecimal.ZERO) >= 0) {
            Transaction transactionEntry=new Transaction();
            transactionEntry.setDetails("Account credit");
            long trxId=(long) (Math.random()*Math.pow(10,10));
            transactionEntry.setAmount(transaction.getAmount());
            transactionEntry.setTransactionReference(trxId);
            userAccountDetails.getTransactions().add(transactionEntry);
            userAccountDetails.setAmount(availableBalance);
            userAccountRepository.save(userAccountDetails);
            AddMoneyResponse addMoneyResponse=new AddMoneyResponse();
            SuccessResponse successResponse=new SuccessResponse();
            successResponse.setStatus(true);
            successResponse.setSuccessMsg("Account has been credited successfully");
            addMoneyResponse.setSuccess(successResponse);
            addMoneyResponse.setTransactionId(Long.valueOf(trxId));
            return addMoneyResponse;
        }
        throw new BalanceLowException(String.format("user's balance is %.2f and cannot perform a transaction of %.2f ",balance.doubleValue(), transaction.getAmount().doubleValue()),403);
        }

    @Override
    @Transactional
    public List<Transaction> transfer(TransactionRequestWO walletDTO, Long toUserAccountId, Long fromUserAccountId) {
        List<Transaction> transactions = new ArrayList<>();

        if (accountService.userAccountByPK(fromUserAccountId) == null)
           throw new UserNotFoundException(String.format("userAccount with '%d' not found ", fromUserAccountId),404);
        if (accountService.userAccountByPK(toUserAccountId) == null) {
            throw new UserNotFoundException(String.format("userAccount with '%d' not found ", toUserAccountId),404);
        }
        Transaction sourceUserTransaction;
        Transaction destinationUserTransaction;

        walletDTO.setUserAccountId(fromUserAccountId);
        walletDTO.setAmount(walletDTO.getAmount().negate());
        sourceUserTransaction = null;//createTransaction(TransactionMapper.dtoToDO(walletDTO));
        transactions.add(sourceUserTransaction);

        walletDTO.setUserAccountId(toUserAccountId);
        walletDTO.setAmount(walletDTO.getAmount().negate());
        destinationUserTransaction =null;// createTransaction(TransactionMapper.dtoToDO(walletDTO));
        transactions.add(destinationUserTransaction);

        return transactions;
    }

    @Override
    public CalculateChargeCommissionResponse calculateChargeCommission(CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {
        return null;
    }
}
