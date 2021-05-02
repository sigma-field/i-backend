package com.bb.recipes.repo;

import javax.transaction.Transactional;

import com.bb.recipes.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

}