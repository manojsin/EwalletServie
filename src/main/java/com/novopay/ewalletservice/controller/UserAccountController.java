package com.novopay.ewalletservice.controller;

import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.service.TransactionService;
import com.novopay.ewalletservice.service.UserAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserAccountController {

    private UserAccountService userAccountService;
    private TransactionService transactionService;
    @Autowired
    public  UserAccountController(UserAccountService userAccountService,
                                 TransactionService transactionService)
    {
        this.userAccountService=userAccountService;
        this.transactionService=transactionService;
    }
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get All users ", response = List.class)
    public ResponseEntity getUsers() {
        List<UserAccount> userAccounts = userAccountService.getList();
        return new ResponseEntity<>("f", HttpStatus.OK);
    }
}
