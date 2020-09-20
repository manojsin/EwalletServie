package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.*;
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
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public ResponseEntity<?> transferMoney(@RequestBody TransferMoneyRequestWO requestWO) {
        TransferMoneyResponse transferMoneyResponse= transactionService.transfer(requestWO);
        return new ResponseEntity<>(transferMoneyResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculate/chargeCommission",method = RequestMethod.POST)
    public ResponseEntity<?> calculateChargeCommission(@RequestBody CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {
        CalculateChargeCommissionResponse chargeCommission = transactionService.calculateChargeCommission(calculateChargeCommissionRequestWO);
        return new ResponseEntity<>(chargeCommission, HttpStatus.OK);
    }

}
