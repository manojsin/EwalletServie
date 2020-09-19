package com.novopay.ewalletservice.controller;
import com.novopay.ewalletservice.entity.Transaction;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.UserAccountRequestWO;
import com.novopay.ewalletservice.service.TransactionService;
import com.novopay.ewalletservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserAccountRequestWO userAccountRequestWO) {
        UserAccount saved = userAccountService.save(null);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
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
    }

    @RequestMapping(value = "/{id}/passbook",method = RequestMethod.GET)
    public ResponseEntity<?> getUserPassbook(@PathVariable("id") Long id) {
        List<Transaction> passbook=transactionService.transactionsByUserAccountID(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
