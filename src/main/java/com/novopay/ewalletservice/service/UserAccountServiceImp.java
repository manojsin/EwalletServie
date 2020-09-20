package com.novopay.ewalletservice.service;

import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.CreateUserAccountResponse;
import com.novopay.ewalletservice.model.SuccessResponse;
import com.novopay.ewalletservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class UserAccountServiceImp implements UserAccountService {

    private UserAccountRepository userAccountRepository;
    @Autowired
    public UserAccountServiceImp(UserAccountRepository userAccountRepository)
    {
        this.userAccountRepository=userAccountRepository;
    }

    @Override
    public CreateUserAccountResponse save(UserAccount userAccount) {
        CreateUserAccountResponse createUserAccountResponse=new CreateUserAccountResponse();
        UserAccount userAccount1= userAccountRepository.save(userAccount);
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setStatus(true);
        successResponse.setSuccessMsg("Account has been create successfully");
        createUserAccountResponse.setSuccess(successResponse);
        createUserAccountResponse.setAccountId(userAccount1.getAccountNo());
        return createUserAccountResponse;
    }

    @Override
    public UserAccount update(UserAccount userAccount, Long id) {
        userAccount.setAccountNo(id);
        UserAccount update= userAccountRepository.save(userAccount);
        return update;
    }

    @Override
    public List<UserAccount> getList() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount userAccountByPK(Long accountId) {
        //return userAccountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException(String.format("userAccount with '%d' not found ", accountId)));
        return null;
    }
}
