package com.novopay.ewalletservice.service;
import com.novopay.ewalletservice.entity.UserAccount;
import java.util.List;
public interface UserAccountService {
    UserAccount save(UserAccount t);
    UserAccount update(UserAccount t, Long id);
    List<UserAccount> getList();
    UserAccount userAccountByPK(Long accountId);
}
