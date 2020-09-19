package com.novopay.ewalletservice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserAccountController {


    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get All users ", response = List.class)
    public ResponseEntity getUsers() {
        List<UserAccount> userAccounts = userAccountService.getList();
        return new ResponseEntity<List<UserAccountDTO>>(UserAccountMapper.doToDTOList(userAccounts), HttpStatus.OK);
    }
}
