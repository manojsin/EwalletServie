package com.novopay.ewalletservice.service;

import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserAccount save(UserAccount t) {
        return null;
    }

    @Override
    public UserAccount update(UserAccount t, Long id) {
        return null;
    }

    @Override
    public List<UserAccount> getList() {
        return null;
    }

    @Override
    public UserAccount userAccountByPK(Long accountId) {
        return null;
    }
}
