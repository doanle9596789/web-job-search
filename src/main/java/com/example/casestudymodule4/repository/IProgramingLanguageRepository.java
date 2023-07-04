package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Account;
import com.example.casestudymodule4.model.ProgramingLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgramingLanguageRepository extends JpaRepository<ProgramingLanguage,Long> {
    Page<ProgramingLanguage> findAllByNameContaining(String name, Pageable pageable);
}
