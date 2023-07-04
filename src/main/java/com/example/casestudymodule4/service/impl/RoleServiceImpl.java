package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.repository.IRoleRepository;
import com.example.casestudymodule4.service.ext.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public Page<Role> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Role findOne(Long id) {
        return iRoleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Role role) {
        iRoleRepository.save(role);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Role findByName(String name) {
        return iRoleRepository.findByName(name);
    }

    @Override
    public Iterable<Role> findAllRole() {
        return iRoleRepository.findAll();
    }
}
