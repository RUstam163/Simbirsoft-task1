package com.example.task1.service.impl;

import com.example.task1.model.Role;
import com.example.task1.repository.RoleRepository;
import com.example.task1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
}
