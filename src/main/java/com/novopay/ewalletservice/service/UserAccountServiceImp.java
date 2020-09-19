package com.novopay.ewalletservice.service;

import com.novopay.ewalletservice.entity.UserAccount;
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
    @Transactional
    public UserAccount save(UserAccount userAccount) {
        UserAccount userAccount1=userAccountRepository.save(userAccount);
        return userAccount1;
    }

    @Override
    public UserAccount update(UserAccount userAccount, Long id) {
        userAccount.setId(id);
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
