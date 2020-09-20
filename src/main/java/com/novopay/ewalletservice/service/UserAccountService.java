package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.CreateUserAccountResponse;
public interface UserAccountService {
    CreateUserAccountResponse save(UserAccount userAccount);
    //todo add some other features
    /*UserAccount update(UserAccount t, Long id);
    List<UserAccount> getList();
    UserAccount userAccountByPK(Long accountId);*/
}
