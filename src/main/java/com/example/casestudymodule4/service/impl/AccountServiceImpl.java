package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Account;
import com.example.casestudymodule4.model.DTO.AccountPrinciple;
import com.example.casestudymodule4.repository.IAccountRepository;
import com.example.casestudymodule4.service.ext.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Account> findAll(String gmail, Pageable pageable) {
        return accountRepository.findAllByGmailContaining(gmail, pageable);
    }

    @Override
    public Account findOne(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findByGmail(String gmail) {
        return accountRepository.findByGmail(gmail);
    }

    @Override
    public Boolean existsByGmail(String gmail) {
        return accountRepository.existsByGmail(gmail);
    }

    @Override
    public Account save1(Account account) {
        return accountRepository.save(account);
    }
    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByGmail(gmail);
        if (accountOptional.isEmpty()){
            throw new UsernameNotFoundException(gmail);
        }
        return AccountPrinciple.build(accountOptional.get());
    }
}
