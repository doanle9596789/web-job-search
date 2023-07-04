package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.repository.IUserRepository;
import com.example.casestudymodule4.service.ext.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public Page<User> findAll(String name, Pageable pageable) {
        return userRepository.findAllByNameContaining(name , pageable);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
