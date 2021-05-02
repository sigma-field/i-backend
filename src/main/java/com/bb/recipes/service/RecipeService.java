package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Recipe;

public interface RecipeService {
    public List<Recipe> findAll();

    public Recipe findById(Long id);

    public Recipe save(Recipe recipe);

    public void deleteById(Long id);
}