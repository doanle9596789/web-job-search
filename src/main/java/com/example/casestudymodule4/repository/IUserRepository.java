package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Job;
import com.example.casestudymodule4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
    Page<User> findAllByNameContaining(String name, Pageable pageable);

}
