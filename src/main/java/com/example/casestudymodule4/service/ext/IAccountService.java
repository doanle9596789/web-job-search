package com.example.casestudymodule4.service.ext;

import com.example.casestudymodule4.model.Account;
import com.example.casestudymodule4.service.core.ICoreService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAccountService extends ICoreService<Account>, UserDetailsService {
    Optional<Account> findByGmail(String gmail);
    Boolean existsByGmail(String gmail);
    Account save1(Account account);
}
