package com.example.casestudymodule4.service.ext;

import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.repository.IRoleRepository;
import com.example.casestudymodule4.service.core.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface IRoleService extends ICoreService<Role> {
    Role findByName(String name);
    Iterable<Role> findAllRole();
}
