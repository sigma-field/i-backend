package com.bb.recipes.repo;

import java.util.List;

import javax.transaction.Transactional;

import com.bb.recipes.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public List<Category> findAllByOrderByNameDesc();
}