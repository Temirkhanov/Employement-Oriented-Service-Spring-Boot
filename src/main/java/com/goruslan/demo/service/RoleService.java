package com.goruslan.demo.service;

import com.goruslan.demo.domain.Role;
import com.goruslan.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(Role user) {
        return roleRepository.save(user);
    }
}