package com.novopay.ewalletservice.mapper;

import com.novopay.ewalletservice.entity.UserAccount;
import com.novopay.ewalletservice.model.UserAccountRequestWO;
import org.springframework.stereotype.Component;
@Component
public class ModelToEntityConverter {
    public UserAccount convert(UserAccountRequestWO userAccountRequestWO){
        UserAccount userAccount=new UserAccount();
        userAccount.setFirstName(userAccountRequestWO.getFirstName());
        userAccount.setLastName(userAccountRequestWO.getLastName());
        userAccount.setEmail(userAccountRequestWO.getEmail());
        userAccount.setMobileNumber(userAccountRequestWO.getMobileNumber());
        return userAccount;
    }
}
