package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.exception.CommonUtil;
import com.novopay.ewalletservice.model.*;
import com.novopay.ewalletservice.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> addMoney(@Validated @RequestBody TransactionRequestWO requestWO, BindingResult error) {
        CommonUtil.validateRequest(error);
        AddMoneyResponse addMoneyResponse = transactionService.createTransaction(requestWO);
        return new ResponseEntity<>(addMoneyResponse, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Pay from wallet ", response = TransferMoneyResponse.class, tags = "transact")
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public ResponseEntity<?> transferMoney(@Validated @RequestBody TransferMoneyRequestWO requestWO,BindingResult error) {
        CommonUtil.validateRequest(error);
        TransferMoneyResponse transferMoneyResponse= transactionService.transfer(requestWO);
        return new ResponseEntity<>(transferMoneyResponse, HttpStatus.OK);
    }
    @ApiOperation(value = "Calculate commission and Charge ", response = CalculateChargeCommissionResponse.class, tags = "transact")
    @RequestMapping(value = "/calculate/chargeCommission",method = RequestMethod.POST)
    public ResponseEntity<?> calculateChargeCommission(@Validated @RequestBody CalculateChargeCommissionRequestWO calculateChargeCommissionRequestWO,BindingResult error) {
        CommonUtil.validateRequest(error);
        CalculateChargeCommissionResponse chargeCommission = transactionService.calculateChargeCommission(calculateChargeCommissionRequestWO);
        return new ResponseEntity<>(chargeCommission, HttpStatus.OK);
    }

    @ApiOperation(value = "Reverse Transaction ", response = TransferMoneyResponse.class, tags = "transact")
    @RequestMapping(value = "/reverse/{transactionId}/transaction",method = RequestMethod.GET)
    public ResponseEntity<?> reverseTransaction(@PathVariable("transactionId") Long transactionId) {
        TransferMoneyResponse reverseTransaction = transactionService.reverseTransaction(transactionId);
        return new ResponseEntity<>(reverseTransaction, HttpStatus.OK);
    }

}
