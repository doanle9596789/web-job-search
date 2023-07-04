package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Page<Account> findAllByGmailContaining(String gmail, Pageable pageable);
    Optional<Account> findByGmail(String gmail);
    Boolean existsByGmail(String gmail);
}
