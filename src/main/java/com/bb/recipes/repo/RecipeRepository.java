package com.bb.recipes.repo;

import java.util.Collection;

import javax.transaction.Transactional;

import com.bb.recipes.entities.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
@RepositoryRestResource
@Transactional
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    public Collection<Recipe> findRecipesByCategoryId(Integer categoryId);

}