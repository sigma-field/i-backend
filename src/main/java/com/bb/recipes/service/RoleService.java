package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Role;

public interface RoleService {

    public List<Role> findAll();

    public Role findById(Long id);

    public Role save(Role role);

    public void deleteById(Long id);
}