package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.model.*;
import com.novopay.ewalletservice.service.TransactionService;
import com.novopay.ewalletservice.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/account")
@Api(value = "E Wallet TransactionController")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService=transactionService;
    }
    @ApiOperation(value = "Add Money in Wallet ", response = AddMoneyResponse.class, tags = "transact")
    @RequestMapping(value = "/addMoney",method = RequestMethod.POST)
    public ResponseEntity<?> addMoney(@RequestBody TransactionRequestWO requestWO) {
        AddMoneyResponse addMoneyResponse = transactionService.createTransaction(requestWO);
        return new ResponseEntity<>(addMoneyResponse, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Pay from wallet ", response = TransferMoneyResponse.class, tags = "transact")
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public ResponseEntity<?> transferMoney(@RequestBody TransferMoneyRequestWO requestWO) {
        TransferMoneyResponse transferMoneyResponse= transactionService.transfer(requestWO);
        return new ResponseEntity<>(transferMoneyResponse, HttpStatus.OK);
    }
    @ApiOperation(value = "Calculate commission and Charge ", response = CalculateChargeCommissionResponse.class, tags = "transact")
    @RequestMapping(value = "/calculate/chargeCommission",method = RequestMethod.POST)
    public ResponseEntity<?> calculateChargeCommission(@RequestBody CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO) {
        CalculateChargeCommissionResponse chargeCommission = transactionService.calculateChargeCommission(calculateChargeCommissionRequestWO);
        return new ResponseEntity<>(chargeCommission, HttpStatus.OK);
    }

}
