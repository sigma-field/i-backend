package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Category;

public interface CategoryService {
    public List<Category> findAll();

    public Category findById(Integer id);

    public Category save(Category category);

    public void deleteById(Integer id);
}