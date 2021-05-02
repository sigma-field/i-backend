package com.bb.recipes.repo;

import javax.transaction.Transactional;

import com.bb.recipes.entities.AboutUs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(path = "about-us")
@Transactional
public interface AboutUsRepository extends JpaRepository<AboutUs, Long> {

}