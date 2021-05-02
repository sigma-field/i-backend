package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Role;
import com.bb.recipes.repo.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {

        return roleRepository.save(role);
    }

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {

        return roleRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);

    }

}