package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.AddMoneyResponse;
import com.novopay.ewalletservice.model.CalculateChargeCommissionRequestWO;
import com.novopay.ewalletservice.model.CalculateChargeCommissionResponse;
import com.novopay.ewalletservice.model.TransactionRequestWO;
import com.novopay.ewalletservice.service.TransactionService;
import com.novopay.ewalletservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/account")
public class TransactionController {

    private UserAccountService userAccountService;
    private TransactionService transactionService;

    @Autowired
    public TransactionController(UserAccountService userAccountService,TransactionService transactionService)
    {
        this.userAccountService=userAccountService;
        this.transactionService=transactionService;
    }
    @RequestMapping(value = "/addMoney",method = RequestMethod.POST)
    public ResponseEntity<?> addMoney(@RequestBody TransactionRequestWO requestWO) {
        AddMoneyResponse addMoneyResponse = transactionService.createTransaction(requestWO);
        return new ResponseEntity<>(addMoneyResponse, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{toUser}/from/{fromUser}",method = RequestMethod.POST)
    public ResponseEntity<?> transferMoney(@PathVariable("toUser") Long toUserAccountId, @PathVariable("fromUser") Long fromUserAccountId, @RequestBody TransactionRequestWO requestWO) {
        List<Transaction> both = transactionService.transfer(requestWO, toUserAccountId, fromUserAccountId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/calculate/chargeCommission",method = RequestMethod.POST)
    public ResponseEntity<?> calculateChargeCommission(@RequestBody CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {
        CalculateChargeCommissionResponse chargeCommission = transactionService.calculateChargeCommission(calculateChargeCommissionRequestWO);
        return new ResponseEntity<>(chargeCommission, HttpStatus.OK);
    }

}
