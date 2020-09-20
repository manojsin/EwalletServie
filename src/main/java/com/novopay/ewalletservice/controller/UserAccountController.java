package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.mapper.ModelToEntityConverter;
import com.novopay.ewalletservice.model.CalculateChargeCommissionResponse;
import com.novopay.ewalletservice.model.CreateUserAccountResponse;
import com.novopay.ewalletservice.model.UserAccountRequestWO;
import com.novopay.ewalletservice.service.TransactionService;
import com.novopay.ewalletservice.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("v1/users")
@Api(value = "E Wallet UserAccountController")
public class UserAccountController {

    private UserAccountService userAccountService;
    private TransactionService transactionService;
    private ModelToEntityConverter modelToEntityConverter;
    @Autowired
    public  UserAccountController(UserAccountService userAccountService,
                                 TransactionService transactionService,
                                  ModelToEntityConverter modelToEntityConverter)
    {
        this.userAccountService=userAccountService;
        this.transactionService=transactionService;
        this.modelToEntityConverter=modelToEntityConverter;
    }
    @ApiOperation(value = "Create User Account ", response = CalculateChargeCommissionResponse.class, tags = "transact")
    @RequestMapping(value = "/account/create",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserAccountRequestWO userAccountRequestWO) {
        UserAccount userAccount= modelToEntityConverter.convert(userAccountRequestWO);
        CreateUserAccountResponse createUserAccountResponse = userAccountService.save(userAccount);
        return new ResponseEntity<>(createUserAccountResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/passbook",method = RequestMethod.GET)
    public ResponseEntity<?> getUserPassbook(@PathVariable("id") Long id) {
        UserAccount passbook=transactionService.transactionsByUserAccountID(id);
        return new ResponseEntity<>(passbook, HttpStatus.OK);
    }

    /*@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userAccountId, @RequestBody UserAccountRequestWO userAccountRequestWO) {
        UserAccount saved = userAccountService.update(null, userAccountId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<UserAccount> userAccounts = userAccountService.getList();
        return new ResponseEntity<>("f", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        UserAccount userAccount = userAccountService.userAccountByPK(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }*/

}
