package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.exception.BalanceLowException;
import com.novopay.ewalletservice.exception.UserNotFoundException;
import com.novopay.ewalletservice.model.*;
import com.novopay.ewalletservice.repository.TransactionRepository;
import com.novopay.ewalletservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    private UserAccountRepository userAccountRepository;
    private TransactionRepository transactionRepository;
    private Environment environment;
    @Autowired
    public TransactionServiceImp(UserAccountRepository userAccountRepository,
                                 TransactionRepository transactionRepository,
                                 Environment environment)
    {
        this.userAccountRepository=userAccountRepository;
        this.transactionRepository=transactionRepository;
        this.environment=environment;
    }

    @Override
    public UserAccount transactionsByUserAccountID(Long accountId) {
        return userAccountRepository.findAllByAccountNo(accountId);
    }
    @Override
    @Transactional
    public AddMoneyResponse createTransaction(TransactionRequestWO transaction) {
        UserAccount userAccountDetails=userAccountRepository.findAllByAccountNo(transaction.getUserAccountId());
        Optional<BigDecimal> balanceCheck=Optional.ofNullable(userAccountDetails.getAvailableBal());
        BigDecimal balance=balanceCheck.isPresent()? userAccountDetails.getAvailableBal():new BigDecimal(0);
        BigDecimal availableBalance=balance.add(transaction.getAmount());
        if (availableBalance.compareTo(BigDecimal.ZERO) >= 0) {
            long trxId=(long) (Math.random()*Math.pow(10,10));
            userAccountDetails.getTransactions().add(getTransactionDetails(transaction.getAmount(),"Account credit",trxId,transaction.getUserAccountId()));
            userAccountDetails.setAvailableBal(availableBalance);
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
        Optional<BigDecimal> balanceCheck=Optional.ofNullable(senderAccount.getAvailableBal());
        BigDecimal senderAvlBalance=balanceCheck.isPresent()? senderAccount.getAvailableBal():new BigDecimal(0);
        if(senderAvlBalance.compareTo(walletDTO.getAmount())<0) {
            throw new BalanceLowException(String.format("user's balance is %.2f and cannot perform a transaction of %.2f ",senderAvlBalance, walletDTO.getAmount().doubleValue()),403);
        }
        long trxId=(long) (Math.random()*Math.pow(10,10));
        //sender balance
         senderAvlBalance=senderAvlBalance.subtract(walletDTO.getAmount());
         senderAccount.setAvailableBal(senderAvlBalance);
         senderAccount.getTransactions().add(getTransactionDetails(walletDTO.getAmount(),"Amount debit",trxId,walletDTO.getToAccountNo()));
         //receiver balance
        Optional<BigDecimal> receiverBal=Optional.ofNullable(receiverAccount.getAvailableBal());
        BigDecimal receiverTotal=receiverBal.isPresent()? receiverAccount.getAvailableBal():new BigDecimal(0);
        receiverTotal=receiverTotal.add(walletDTO.getAmount());
        receiverAccount.setAvailableBal(receiverTotal);
        receiverAccount.getTransactions().add(getTransactionDetails(walletDTO.getAmount(),"Amount credit",trxId,walletDTO.getFormAccountNo()));
        userAccountRepository.save(senderAccount);
        userAccountRepository.save(receiverAccount);
        return new TransferMoneyResponse(walletDTO,senderAvlBalance);
    }

    @Override
    public CalculateChargeCommissionResponse calculateChargeCommission(CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {

        BigDecimal amount= Optional.ofNullable(calculateChargeCommissionRequestWO).isPresent()?calculateChargeCommissionRequestWO.getAmount():new BigDecimal(0);
        BigDecimal chargeAmount=new BigDecimal(0);
        BigDecimal commissionAmount=new BigDecimal(0);
        BigDecimal chargeRate=new BigDecimal(0);
        BigDecimal commissionRate=new BigDecimal(0);
        if(calculateChargeCommissionRequestWO.isChargeApplicable()) {
            chargeRate=BigDecimal.valueOf(Double.valueOf(environment.getProperty("payment.charge-rate")));
            chargeAmount=calculateExtraCharge(amount,chargeRate);
        }
        if(calculateChargeCommissionRequestWO.isCommissionApplicable()) {
            commissionRate=BigDecimal.valueOf(Double.valueOf(environment.getProperty("payment.commission.rate")));
            commissionAmount=calculateExtraCharge(amount,commissionRate);
        }
        calculateChargeCommissionRequestWO.getAmount();
        CalculateChargeCommissionResponse response= new CalculateChargeCommissionResponse(amount,chargeRate,commissionRate,chargeAmount,commissionAmount);
        response.setTotalAmount(amount.add(chargeAmount).add(commissionAmount));
        return response;
    }

    @Override
    @Transactional
    public TransferMoneyResponse reverseTransaction(Long transactionId) {
        List<Transaction> transactionData=transactionRepository.getTransactionByRef(transactionId);
        if(transactionData==null) {
        throw new UserNotFoundException(String.format("transactionId details  with '%d' not found ", transactionId),404);
       }
        transactionData=transactionData.stream().filter(data->data.getDetails().equalsIgnoreCase("Amount credit")).collect(Collectors.toList());
        TransferMoneyRequestWO transactionDetails=new TransferMoneyRequestWO();
        transactionDetails.setAmount(transactionData.get(0).getAmount());
        transactionDetails.setToAccountNo(transactionData.get(0).getTransactionAccountNo());
        transactionDetails.setFormAccountNo(transactionData.get(0).getAccountNo());
       return  this.transfer(transactionDetails);
    }

    private Transaction getTransactionDetails(BigDecimal amount,String message,Long trxId,Long transactionAccNo) {
        Transaction transactionEntry=new Transaction();
        transactionEntry.setDetails(message);
        transactionEntry.setAmount(amount);
        transactionEntry.setTransactionReference(trxId);
        transactionEntry.setTransactionAccountNo(transactionAccNo);
        return transactionEntry;
    }

    private BigDecimal calculateExtraCharge(BigDecimal baseAmount,BigDecimal rate)
    {
        BigDecimal amount=baseAmount.multiply(rate, new MathContext(2));
        return amount.divide(new BigDecimal(100),new MathContext(2));
    }
}
