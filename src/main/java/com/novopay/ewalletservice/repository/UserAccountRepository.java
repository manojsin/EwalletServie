package com.novopay.ewalletservice.repository;
import com.novopay.ewalletservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findAllByAccountNo(Long userAccountId);
   /* @Modifying
    UserAccount save(UserAccount userAccount);*/
    //Optional<UserAccount> getByUserName(String name);
}
