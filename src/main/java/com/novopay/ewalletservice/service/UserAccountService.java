package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.CreateUserAccountResponse;

import java.util.List;
public interface UserAccountService {
    CreateUserAccountResponse save(UserAccount userAccount);
    UserAccount update(UserAccount t, Long id);
    List<UserAccount> getList();
    UserAccount userAccountByPK(Long accountId);
}
