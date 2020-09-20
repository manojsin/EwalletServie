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
            long trxId=(long) (Math.random()*Math.pow(10,10));
            userAccountDetails.getTransactions().add(getTransactionDetails(transaction.getAmount(),"Account credit",trxId));
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
    public TransferMoneyResponse transfer(TransferMoneyRequestWO walletDTO) {
        UserAccount senderAccount=userAccountRepository.findAllByAccountNo(walletDTO.getFormAccountNo());
        UserAccount receiverAccount=userAccountRepository.findAllByAccountNo(walletDTO.getToAccountNo());
        if (receiverAccount==null)
           throw new UserNotFoundException(String.format("userAccount with '%d' not found ", walletDTO.getToAccountNo()),404);
        if (senderAccount==null) {
            throw new UserNotFoundException(String.format("userAccount with '%d' not found ", walletDTO.getFormAccountNo()),404);
        }
        Optional<BigDecimal> balanceCheck=Optional.ofNullable(senderAccount.getAmount());
        BigDecimal senderAvlBalance=balanceCheck.isPresent()? senderAccount.getAmount():new BigDecimal(0);
        if(senderAvlBalance.compareTo(walletDTO.getAmount())<=0) {
            throw new BalanceLowException(String.format("user's balance is %.2f and cannot perform a transaction of %.2f ",senderAvlBalance, walletDTO.getAmount().doubleValue()),403);
        }
        long trxId=(long) (Math.random()*Math.pow(10,10));
        //sender balance
         senderAvlBalance=senderAvlBalance.subtract(walletDTO.getAmount());
         senderAccount.setAmount(senderAvlBalance);
         senderAccount.getTransactions().add(getTransactionDetails(walletDTO.getAmount(),"Amount debit",trxId));
         //receiver balance
        Optional<BigDecimal> receiverBal=Optional.ofNullable(receiverAccount.getAmount());
        BigDecimal receiverTotal=receiverBal.isPresent()? receiverAccount.getAmount():new BigDecimal(0);
        receiverTotal=receiverTotal.add(walletDTO.getAmount());
        receiverAccount.setAmount(receiverTotal);
        receiverAccount.getTransactions().add(getTransactionDetails(walletDTO.getAmount(),"Amount credit",trxId));
        userAccountRepository.save(senderAccount);
        userAccountRepository.save(receiverAccount);
        return new TransferMoneyResponse(walletDTO,senderAvlBalance);
    }

    @Override
    public CalculateChargeCommissionResponse calculateChargeCommission(CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {
        return null;
    }
    private Transaction getTransactionDetails(BigDecimal amount,String message,Long trxId) {
        Transaction transactionEntry=new Transaction();
        transactionEntry.setDetails(message);
        transactionEntry.setAmount(amount);
        transactionEntry.setTransactionReference(trxId);
        return transactionEntry;
    }
}
